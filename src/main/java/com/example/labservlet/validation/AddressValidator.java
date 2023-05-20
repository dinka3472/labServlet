package com.example.labservlet.validation;

import com.example.labservlet.models.entitys.Address;

import java.util.List;

public interface AddressValidator {

    boolean AddressValidate(Address addresses);

    boolean AddressesValidate(List<Address> addresses);
}
