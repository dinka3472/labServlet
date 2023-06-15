package com.example.labservlet.DBManager;

import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;

import jakarta.annotation.ManagedBean;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Singleton

public class DBManagerImpl implements DBManager {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> getAllClients() {
        Collator collator = Collator.getInstance(new Locale("ru"));
        return entityManager.createQuery("SELECT c FROM Client c", Client.class)
                .getResultList()
                .stream()
                .sorted((c1, c2) -> collator.compare(c1.getClientName(), c2.getClientName()))
                .collect(Collectors.toList());
    }

    @Override
    public Client getClientById(Integer id) {
        return (Client) entityManager
                .createQuery("SELECT c FROM  Client c WHERE c.clientId = ?1").setParameter(1, id)
                .getSingleResult();
    }

    @Override
    public void createClient(Client client) {
        entityManager.persist(client);
    }

    @Override
    public void updateClient(Client client) {
        entityManager.merge(client);
    }

    @Override
    public void deleteClientById(Integer id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
        }
    }

    @Override
    public List<Client> getClientsByFilters(ClientType clientType, String searchText) {
        Collator collator = Collator.getInstance(new Locale("ru"));
        String jpql = "SELECT DISTINCT c FROM Client c JOIN c.addresses ad WHERE (:clientType IS NULL OR c.type = :clientType) " +
                "AND (:searchText IS NULL OR LENGTH(:searchText) = 0 " +
                "OR (LOWER(ad.address) LIKE LOWER(:searchText) OR LOWER(c.clientName) LIKE LOWER(:searchText)))";
        return entityManager.createQuery(jpql, Client.class)
                .setParameter("clientType", clientType)
                .setParameter("searchText", "%" + searchText + "%")
                .getResultList()
                .stream()
                .sorted((c1, c2) -> collator.compare(c1.getClientName(), c2.getClientName()))
                .collect(Collectors.toList());
    }
}
