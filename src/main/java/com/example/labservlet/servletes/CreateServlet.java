package com.example.labservlet.servletes;

import com.example.labservlet.ServiceBeanses.UpdateBean;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateServlet", value = "/CreateServlet")
public class CreateServlet extends ClientServlet {
    @Inject
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("clientTypes", ClientType.values());
        request.getRequestDispatcher("addClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateBean.createClient(createClientFromRequest(request, response));
        response.sendRedirect("/labServlet-1.0-SNAPSHOT/");
    }
}
