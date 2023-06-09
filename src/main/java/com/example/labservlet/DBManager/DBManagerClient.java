package com.example.labservlet.DBManager;

import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface DBManagerClient {
    public List<Client> getAllClients();
    public Client getClientById(Integer id);
    public void createClient(Client client);
    public Client updateClient(Client client);
    public  void deleteClientById(Integer id);

    List<Client> getClientsByFilters(ClientType clientType, String searchText);
}
