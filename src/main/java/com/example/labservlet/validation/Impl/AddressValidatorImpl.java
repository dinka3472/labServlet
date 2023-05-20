package com.example.labservlet.validation.Impl;

import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.validation.AddressValidator;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

@Stateless
public class AddressValidatorImpl implements AddressValidator {
    @Inject
    private Validator validator;


    @Override
    public boolean AddressValidate(Address address) {
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        return violations.isEmpty();
    }

    @Override
    public boolean AddressesValidate(List<Address> addresses) {
        Set<ConstraintViolation<List<Address>>> violations = validator.validate(addresses);
        return violations.isEmpty();
    }
}
