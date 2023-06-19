package com.example.labservlet.ServiceBeanses;

import com.example.labservlet.DBManager.DBManagerAddress;
import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.DBManager.DBManagerClient;
import com.example.labservlet.models.enums.ClientType;
import com.example.labservlet.validation.AddressValidator;
import com.example.labservlet.validation.ClientValidator;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


import java.util.List;

@Stateless
public class SelectBean {
    @Inject
    private DBManagerClient dbManager;
    @Inject
    private DBManagerAddress dbManagerAddress;
    @Inject
    private ClientValidator clientValidator;
    @Inject
    private AddressValidator addressValidator;

   public List<Client> getAllClients() {
       return dbManager.getAllClients();
    }


    public Client getClientById(Integer id) {
       clientValidator.clientIsExistById(id);
       return dbManager.getClientById(id);
    }

    public List<Client> getClientsByFilters(ClientType clientType, String searchText) {
       return dbManager.getClientsByFilters(clientType, searchText);
    }

    public List<Address> getAllAddresses() {
       return dbManagerAddress.getAllAddresses();
    }

    public Address getAddressById(Integer id) {
       addressValidator.addressIsExistById(id);
       return dbManagerAddress.getAddressById(id);
    }
}
