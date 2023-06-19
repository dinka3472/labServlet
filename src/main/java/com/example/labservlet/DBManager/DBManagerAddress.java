package com.example.labservlet.DBManager;

import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.models.entitys.Client;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface DBManagerAddress {
    public List<Address> getAllAddresses();
    public Address getAddressById(Integer id);
    public void createAddress(Address address);
    public Address updateAddress(Address address);
    public  void deleteAddressById(Integer id);

}
