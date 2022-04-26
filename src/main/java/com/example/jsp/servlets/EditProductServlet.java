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

@WebServlet(urlPatterns = {"/editProduct"})
public class EditProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditProductServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection conn = MyUtils.getStoredConnection(request);

        String code = (String) request.getParameter("code");

        Product product = null;
        String errorString = null;

        try{
            product = DBUtils.findProduct(conn,code);
        }catch(SQLException ex){
            ex.printStackTrace();
            errorString = ex.getMessage();
        }
        if(errorString != null && product == null){
            response.sendRedirect(request.getServletPath() + "/productList");
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("product",product);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/editProductView.jsp");
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection conn = MyUtils.getStoredConnection(request);

        String code = (String) request.getParameter("code");
        String name = (String) request.getParameter("name");
        String charprice = (String) request.getParameter("price");

        Float price = Float.valueOf(charprice);

        Product product = new Product(code,name,price);

        String errorString = null;

        try{
            DBUtils.updateProduct(conn, product);
        }catch(SQLException e){
            e.printStackTrace();
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        if(errorString!= null){
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/editProductView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }
}
