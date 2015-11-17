package com.pavel.gargolin;



import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by PCPC on 17.11.2015.
 */
public class BettterServlet extends HttpServlet {
    public void init()throws ServletException
    {
// Do required initialization

    }
    public void destroy() {

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.addCookie(new Cookie("pavel", "gargolin"));
        System.out.println(response.toString());
        PrintWriter out= response.getWriter();
        String title ="Using GET Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 "+

                        "transitional//en\">\n";
        StringBuilder f=new StringBuilder();
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()){
            String paramName =(String)paramNames.nextElement();
          f.append("<ul>"+ paramName +"</ul>");
            String[] paramValues =
                    request.getParameterValues(paramName);
// Read single valued data
            for( String g:paramValues) {

// Read multiple valued data
                f.append("<ul>");
                f.append(g);
                f.append("</ul>");
            }
        }
        out.print(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                f +
                "</ul>\n" +
                "<a href='/new.html'>click here</a>"+
                "</body></html>");
    }
}
