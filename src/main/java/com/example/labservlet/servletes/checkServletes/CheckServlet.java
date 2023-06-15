package com.example.labservlet.servletes.checkServletes;

import com.example.labservlet.ServiceBeanses.SelectBean;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.util.Transformer;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckServlet", value = "/CheckServlet")
public class CheckServlet extends HttpServlet {
    @Inject
    private SelectBean selectBean;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String searchQuery = request.getParameter("searchQuery");

        String filePath = getServletContext().getInitParameter("clientFile-path");
        Transformer.transformToXML(selectBean.getAllClients(), filePath);

        List<Client> searchResults = parseXML(filePath, searchQuery);

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

    protected List<Client> parseXML(String filePath, String searchQuery) {
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
