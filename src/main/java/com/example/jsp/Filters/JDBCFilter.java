package com.example.jsp.Filters;

import com.example.jsp.dbconn.ConnectionUtils;
import com.example.jsp.myUtils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

@WebFilter(filterName = "jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter {

    public JDBCFilter(){

    }

    @Override
    public void init(FilterConfig fConfig)throws ServletException{

    }

    @Override
    public void destroy(){

    }

    private boolean needJDBC(HttpServletRequest request){

       if(MyUtils.getStoredConnection(request) == null){
           return true;
       }
       return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;

        if(this.needJDBC(req)){ //if no connection then make
            Connection conn = null;
            try{
                conn = ConnectionUtils.getConnection();
                conn.setAutoCommit(false);
                MyUtils.storeConnection(request,conn);
                chain.doFilter(request,response);
                conn.commit();
            }
            catch(Exception e){
                e.printStackTrace();
                ConnectionUtils.rollbackQuietly(conn);
                throw new ServletException();
            }finally {
                ConnectionUtils.closeQuietly(conn);
            }

        }
        else
        {
            chain.doFilter(request,response); //else call next servlet in chain
        }
    }


}
