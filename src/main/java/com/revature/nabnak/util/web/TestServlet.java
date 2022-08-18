package com.revature.nabnak.util.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test") // add path to the url endpoint, only need if you're leveraging the DEFAULT CONSTRUCTOR
public class TestServlet extends HttpServlet {

    // KNOW THIS METHOD SIGNATURE - love asking about it in QC
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("<h1>Welcome to the wonderful world of servlets!!! yayyyyyy</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("<h1>Welcome to the wonderful world of servlets, in doPost!!! yayyyyyy</h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

    }
}
