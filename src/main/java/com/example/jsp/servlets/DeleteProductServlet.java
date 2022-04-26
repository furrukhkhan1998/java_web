package com.example.jsp.servlets;

import com.example.jsp.myUtils.DBUtils;
import com.example.jsp.myUtils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteProduct"})
public class DeleteProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DeleteProductServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String code = (String) request.getParameter("code");
        String errorString = null;

        try{
            DBUtils.deleteProduct(conn,code);
        }catch(SQLException ex){
            ex.printStackTrace();
            errorString = ex.getMessage();

        }

        if(errorString != null){
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/deleteProductErrorView.jsp");
            dispatcher.forward(request,response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }
}
