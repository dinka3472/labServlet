package com.example.labservlet.ServiceBeanses;

import com.example.labservlet.DBManager.DBManagerAddress;
import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.DBManager.DBManagerClient;
import com.example.labservlet.validation.AddressValidator;
import com.example.labservlet.validation.ClientValidator;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
public class UpdateBean {
    @Inject
    private DBManagerClient clientRepository;
    @Inject
    private DBManagerAddress dbManagerAddress;

    @Inject
    private ClientValidator clientValidator;
    @Inject
    private AddressValidator addressValidator;

    public void createClient(Client client) {
        clientRepository.createClient(client);
    }

    public Client updateClient(Client client) {
        clientValidator.validateClient(client);
        clientValidator.clientIsExistById(client.getClientId());
        return clientRepository.updateClient(client);
    }

    public void deleteClient(Integer clientId) {
        clientValidator.clientIsExistById(clientId);
        clientRepository.deleteClientById(clientId);
    }

    public void deleteAddress(Integer id) {
        addressValidator.addressIsExistById(id);
        dbManagerAddress.deleteAddressById(id);
    }

    public Address updateAddress(Address address) {
        addressValidator.addressIsExistById(address.getId());
        return dbManagerAddress.updateAddress(address);
    }

    public void createAddress(Address address) {
        dbManagerAddress.createAddress(address);
    }
}
