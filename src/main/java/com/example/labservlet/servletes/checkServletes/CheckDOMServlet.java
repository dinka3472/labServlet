package com.example.labservlet.servletes.checkServletes;

import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.parsersXML.DemoDom;
import jakarta.servlet.annotation.*;

import java.util.List;

@WebServlet(name = "CheckDOMServlet", value = "/CheckDOMServlet")
public class CheckDOMServlet extends CheckServlet {

    @Override
    protected List<Client> performSearch(String filePath, String searchQuery) {
        DemoDom demoDom = new DemoDom();
        return demoDom.parseXML(filePath, searchQuery);
    }

}
