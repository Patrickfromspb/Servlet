package com.pavel.gargolin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.*;

/**
 * Created by PCPC on 07.11.2015.
 */
class Repeat implements Callable{
volatile int i=0;
    @Override
    public String call() throws Exception {
       i++;
        return  Integer.toString(i)+" "+Thread.currentThread().toString();
    }
}
public class Servlet extends HttpServlet {
    public void init()throws ServletException
    {
// Do required initialization

    }
    public void destroy() {

    }

    public void doGet(HttpServletRequest request,
                     HttpServletResponse response)throws ServletException,IOException
    {
// Set response content type
        response.setContentType("text/html");
        Repeat repeat=new Repeat();
        ExecutorService exec = Executors.newFixedThreadPool(10);

        String[] er= new String[6];
        for(int i=0;i<6;i++) {
            Future<String> future= exec.submit(repeat) ;
            try {
                while(!future.isDone()) {
                    TimeUnit.MILLISECONDS.sleep(40);
                }
                er[i] = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        PrintWriter out= response.getWriter();
        String title ="Using GET Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 "+
                        "transitional//en\">\n";
        String[] arr= request.getParameter("array").split(" ");
        int[] a= new int[arr.length];
        for( int i=0;i<arr.length;i++) a[i]=Integer.valueOf(arr[i]);
        Arrays.sort(a);
        out.print(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                " <li><b>First Name</b>: "
                + request.getParameter("first_name") + "\n" +
                " <li><b>Last Name</b>: "
                + request.getParameter("last_name") + "\n  <li><b>Array</b>:" +
               Arrays.toString(a) + "\n" +
                " <li><b>Last Name</b>: "
                + Arrays.toString(er) + "\n " +
                "</ul>\n" +
                "<a href='/new.html'>click here</a>"+
                "</body></html>");
       // System.out.print(a.toString());
    }
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,IOException{
        doGet(request, response);
    }


}
