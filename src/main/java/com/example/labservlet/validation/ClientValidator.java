package com.example.labservlet.validation;

import com.example.labservlet.models.entitys.Client;


public interface ClientValidator {
    boolean validateClient(Client client);

    void clientIsExistById(Integer clientId);
}
