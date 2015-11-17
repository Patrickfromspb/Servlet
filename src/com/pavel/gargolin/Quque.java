package com.pavel.gargolin;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by PCPC on 17.11.2015.
 */
public class Quque extends HttpServlet {
    BlockingQueue<Integer> quque=new LinkedBlockingQueue<Integer>();
    Random rand=new Random();
@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    System.out.println("lalalalala");
    PrintWriter out = response.getWriter();
    System.out.println(quque);
    out.write("<!doctype html public \"-//w3c//dtd html 4.0 " +

            "transitional//en\">\n");
    String k = "";
    List<Integer> list= new ArrayList<Integer>();
             quque.drainTo(list);
     k=list.toString();
    System.out.println(list);
     out.write("<html><h1>" + k + "</h1></html>");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out=response.getWriter();
        out.write("<!doctype html public \"-//w3c//dtd html 4.0 "+

                "transitional//en\">\n");
        int k=rand.nextInt(10000);
        out.write("<html><h1>"+k+"</h1></html>");
        try {
            quque.put(k);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
