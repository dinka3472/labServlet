package com.example.labservlet.servletes;

import com.example.labservlet.ServiceBeanses.SelectBean;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewListServlet", value = "")
public class ViewListServlet extends HttpServlet {
    @Inject
    private SelectBean selectBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = selectBean.getAllClients();
        request.setAttribute("clients", clients);
        request.setAttribute("clientTypes", ClientType.values());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
