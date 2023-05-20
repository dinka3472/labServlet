package com.example.labservlet.ServiceBeanses;

import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.DBManager.DBManager;
import com.example.labservlet.models.enums.ClientType;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


import java.util.List;

@Stateless
public class SelectBean {
    @Inject
    private DBManager dbManager;

   public List<Client> getAllClients() {
       return dbManager.getAllClients();
    }


    public Client getClientById(Integer id) {
       return dbManager.getClientById(id);
    }

    public List<Client> getClientsByFilters(ClientType clientType, String searchText) {
       return dbManager.getClientsByFilters(clientType, searchText);
    }
}
