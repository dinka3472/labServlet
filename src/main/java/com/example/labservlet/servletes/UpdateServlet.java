package com.example.labservlet.servletes;

import com.example.labservlet.ServiceBeanses.SelectBean;
import com.example.labservlet.ServiceBeanses.UpdateBean;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends CreateServlet {
    @Inject
    private SelectBean selectBean;

    @Inject
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("clientId"));
        Client client = selectBean.getClientById(id);
        request.setAttribute("client", client);
        request.setAttribute("clientTypes", ClientType.values());
        request.getRequestDispatcher("editClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = createClientFromRequest(request, response);
        client.setClientId(Integer.parseInt(request.getParameter("clientId")));

        updateBean.updateClient(client);
        response.sendRedirect("/labServlet-1.0-SNAPSHOT/");
    }
}
