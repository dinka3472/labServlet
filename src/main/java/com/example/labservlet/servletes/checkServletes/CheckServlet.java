package com.example.labservlet.servletes.checkServletes;

import com.example.labservlet.ServiceBeanses.SelectBean;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.parsersXML.Transformer;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckServlet", value = "/CheckServlet")
public abstract class CheckServlet extends HttpServlet {
    @Inject
    private SelectBean selectBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String searchQuery = request.getParameter("searchQuery");

        String filePath = getServletContext().getInitParameter("clientFile-path");
        Transformer.transformToXML(selectBean.getAllClients(), filePath);

        List<Client> searchResults = performSearch(filePath, searchQuery);

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

    protected abstract List<Client> performSearch(String filePath, String searchQuery);
}
