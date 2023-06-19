package com.example.labservlet.validation.Impl;

import com.example.labservlet.DBManager.DBManagerClient;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.validation.ClientValidator;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;


@Stateless
public class ClientValidatorImpl implements ClientValidator {
    @Inject
    private Validator validator;
    @Inject
    private DBManagerClient dbManager;

    @Override
    public boolean validateClient(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        if (!violations.isEmpty()) {
            StringBuilder error = new StringBuilder();
            violations.stream().forEach(v -> error.append(v.getMessage()).append('\n'));
            throw new RuntimeException(error.toString());
        }
        return true;
    }

    @Override
    public void clientIsExistById(Integer clientId) {
        if (dbManager.getClientById(clientId) == null) {
            throw new RuntimeException("Клиент с таким id не существует");
        }
    }
}
