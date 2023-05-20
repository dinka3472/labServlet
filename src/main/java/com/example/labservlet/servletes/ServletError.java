package com.example.labservlet.servletes;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletError", value = "/ServletError")
public class ServletError extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       sendError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendError(request, response);
    }

    private void sendError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String errorMessage = "Произошла ошибка.";
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        }

        String errorPage = "<html>" +
                "<body>" +
                "<h2>Ошибка:</h2>" +
                "<p>" + errorMessage + "</p>" +
                "</body>" +
                "</html>";

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(errorPage);
    }
}
