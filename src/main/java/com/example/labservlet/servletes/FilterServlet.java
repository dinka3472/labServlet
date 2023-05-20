package com.example.labservlet.servletes;

import com.example.labservlet.ServiceBeanses.SelectBean;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FilterServlet", value = "/FilterServlet")
public class FilterServlet extends HttpServlet {
    @Inject
    private SelectBean selectBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String searchText = request.getParameter("searchText");
        String clientTypeString = request.getParameter("clientType");
        ClientType clientType = clientTypeString.isEmpty()? null : ClientType.valueOf(clientTypeString);

        List<Client> filteredClients = selectBean.getClientsByFilters(clientType, searchText);

        request.setAttribute("clients", filteredClients);
        request.setAttribute("clientTypes", ClientType.values());
        request.setAttribute("selectedClientType", clientType);
        request.setAttribute("searchText", searchText);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
