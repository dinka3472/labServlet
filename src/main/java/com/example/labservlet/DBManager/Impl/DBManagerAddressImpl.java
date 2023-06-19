package com.example.labservlet.DBManager.Impl;

import com.example.labservlet.DBManager.DBManagerAddress;
import com.example.labservlet.models.entitys.Address;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DBManagerAddressImpl implements DBManagerAddress {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Address> getAllAddresses() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();

    }

    @Override
    public Address getAddressById(Integer id) {
        return entityManager.createQuery("SELECT a FROM Address a WHERE a.id = ?1", Address.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    @Override
    public void createAddress(Address address) {
        entityManager.persist(address);
    }

    @Override
    public Address updateAddress(Address address) {
        return entityManager.merge(address);
    }

    @Override
    public void deleteAddressById(Integer id) {
        Address address = entityManager.find(Address.class, id);
        entityManager.remove(address);
    }
}
