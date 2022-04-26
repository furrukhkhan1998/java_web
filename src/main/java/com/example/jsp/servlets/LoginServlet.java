package com.example.jsp.servlets;

import com.example.jsp.models.UserAccount;
import com.example.jsp.myUtils.DBUtils;
import com.example.jsp.myUtils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet(){
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr =  request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if(username == null || password == null || username.length()==0 || password.length() == 0){
            hasError = true;
            errorString = "Required username and password";
        }
        else{
            Connection conn = MyUtils.getStoredConnection(request);
            try{
                user = DBUtils.findUser(conn, username, password);
                if(user == null){
                    hasError = true;
                    errorString = "Username or password is invalid";
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if(hasError){
            user = new UserAccount();
            user.setUserName(username);
            user.setPassword(password);

            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/loginView.jsp");

            dispatcher.forward(request,response);
        }
        else //username_pass is correct
        {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session,user);

            if(remember){
                MyUtils.storeUserCookie(response,user);
            }
            else{
                MyUtils.deleteUserCookie(response);
            }
            response.sendRedirect(request.getContextPath() + "/userinfo");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/loginView.jsp");
        dispatcher.forward(request,response);
    }

}
