package com.example.labservlet.servletes;

import com.example.labservlet.ServiceBeanses.UpdateBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Inject
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer clientId = Integer.parseInt(request.getParameter("clientId"));
        updateBean.deleteClient(clientId);
        response.sendRedirect("/labServlet-1.0-SNAPSHOT/");
    }
}
