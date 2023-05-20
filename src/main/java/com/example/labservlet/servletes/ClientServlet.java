package com.example.labservlet.servletes;

import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Stateless
public abstract class ClientServlet extends HttpServlet {


    protected Client createClientFromRequest(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String[] addressIps = request.getParameterValues("addressIp[]");
        String[] addressMacs = request.getParameterValues("addressMac[]");
        String[] addressModels = request.getParameterValues("addressModel[]");
        String[] addressLocations = request.getParameterValues("addressLocation[]");

        Client client = new Client();
        client.setClientName(request.getParameter("clientName"));
        client.setType(ClientType.valueOf(request.getParameter("clientType")));
        client.setAdded(LocalDate.parse(request.getParameter("clientAdded")));

        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < addressIps.length; i++) {
            Address address = new Address();
            address.setIp(addressIps[i]);
            address.setMac(addressMacs[i]);
            address.setModel(addressModels[i]);
            address.setAddress(addressLocations[i]);
            address.setClient(client);
            addresses.add(address);
        }
        client.setAddresses(addresses);
        return client;
    }
}
