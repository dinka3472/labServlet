package com.example.labservlet.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String clientFilePath = sce.getServletContext().getInitParameter("clientFile-path");
    }
}
