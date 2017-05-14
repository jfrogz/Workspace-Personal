package com.jfrogz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            if (accion.equals("login")) {
                getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            } else if (accion.equals("inicio")) {
                getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);
            } else if (accion.equals("iniciarSesion")) {
                getServletContext().getRequestDispatcher("/jsp/postLogin.jsp").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }
    }
}
