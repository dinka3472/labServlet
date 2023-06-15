package com.example.labservlet.servletes.checkServletes;

import com.example.labservlet.ServiceBeanses.SelectBean;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.util.DemoSAX;
import com.example.labservlet.util.Transformer;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "CheckSAXServlet", value = "/CheckSAXServlet")
public class CheckSAXServlet extends CheckServlet {
    @Inject
    private SelectBean selectBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String searchQuery = request.getParameter("searchQuery");

        String filePath = getServletContext().getInitParameter("clientFile-path");
        Transformer.transformToXML(selectBean.getAllClients(), filePath);

        DemoSAX demoSAX = new DemoSAX();
        List<Client> searchResults = demoSAX.parseXML(filePath, searchQuery);

        response.setContentType("text/html;charset=UTF-8");

        if (searchResults.isEmpty()) {
            response.getWriter().println("По вашему запросу ничего не найдено.");
        } else {
            response.getWriter().println("Результаты поиска:");
            response.getWriter().println("<br>");
            for (Client client : searchResults) {
                response.getWriter().println("Клиент: " + client);
                response.getWriter().println("<br><br>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
