package com.example.jsp.servlets;

import com.example.jsp.models.UserAccount;
import com.example.jsp.myUtils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/userInfo"})
public class UserInfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserInfoServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        UserAccount loginedUser = MyUtils.getloginedUser(session);

        if(loginedUser == null){
            response.sendRedirect((request.getContextPath()+ "/login"));
        }
        request.setAttribute("user", loginedUser);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/userInfoView.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request, response);
    }

}
