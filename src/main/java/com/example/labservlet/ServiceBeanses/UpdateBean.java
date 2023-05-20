package com.example.labservlet.ServiceBeanses;

import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.DBManager.DBManager;
import com.example.labservlet.validation.ClientValidator;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
public class UpdateBean {
    @Inject
    private DBManager clientRepository;

    @Inject
    private ClientValidator clientValidator;

    public void createClient(Client client) {
        clientValidator.validateClient(client);
        clientRepository.createClient(client);
    }

    public void updateClient(Client client) {
        clientValidator.validateClient(client);
        clientValidator.clientIsExistById(client.getClientId());
        clientRepository.updateClient(client);
    }

    public void deleteClient(Integer clientId) {
        clientRepository.deleteClientById(clientId);
    }
}
