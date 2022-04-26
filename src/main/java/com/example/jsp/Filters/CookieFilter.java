package com.example.jsp.Filters;

import com.example.jsp.models.UserAccount;
import com.example.jsp.myUtils.DBUtils;
import com.example.jsp.myUtils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {

    public CookieFilter(){}

    @Override
    public void init(FilterConfig fConfig)throws ServletException{

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        UserAccount userInSession = MyUtils.getloginedUser(session);

        if(userInSession != null){
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request,response);
            return;
        }

        Connection conn = MyUtils.getStoredConnection(request);

        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if(checked == null && conn != null){
            String userName = MyUtils.getUserNameInCookie(req);
            try{
                UserAccount user = DBUtils.findUser(conn, userName);
                MyUtils.storeLoginedUser(session, user);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


}
