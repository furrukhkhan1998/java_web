package com.example.jsp.Filters;

import com.example.jsp.dbconn.ConnectionUtils;

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

        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = servletPath + "/";

        if(pathInfo != null){
            urlPattern = servletPath + "/*";
        }

        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();

        Collection<? extends ServletRegistration> values = servletRegistrations.values();

        for(ServletRegistration sr: values){
            Collection<String> mappings = sr.getMappings();
            if(mappings.contains(urlPattern)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;

        if(this.needJDBC(req)){ //if no connection then make
            Connection conn = null;
            try{

            }
            catch(Exception e){
                e.printStackTrace();
                ConnectionUtils.rollbackQuietly(conn);
            }
        }
        else
        {
            chain.doFilter(request,response); //else call next servlet in chain
        }
    }


}
