package com.example.labservlet.servletes.checkServletes;

import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.parsersXML.DemoSAX;
import jakarta.servlet.annotation.*;

import java.util.List;

@WebServlet(name = "CheckSAXServlet", value = "/CheckSAXServlet")
public class CheckSAXServlet extends CheckServlet {

    @Override
    protected List<Client> performSearch(String filePath, String searchQuery) {
        DemoSAX demoSAX = new DemoSAX();
        return demoSAX.parseXML(filePath, searchQuery);
    }


}
