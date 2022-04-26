package com.example.jsp.servlets;

import com.example.jsp.models.Product;
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
import java.util.List;


@WebServlet(urlPatterns = {"/productList"})
public class ProductListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProductListServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection conn = MyUtils.getStoredConnection(request);

        String errorString = null;
        List<Product> list = null;

        try{
            list = DBUtils.queryProduct(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            errorString = throwables.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("productsList", list);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/productListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }

}
