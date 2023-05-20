package com.example.labservlet;

import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Singleton
@Startup
public class DataInitializer {

    private static final List<String> CITIES = Arrays.asList("Москва", "Санкт-Петербург", "Уфа", "Екатеринбург");
    private static final List<String> STREETS = Arrays.asList("Ленина", "Пушкина");
    private static final List<String> HOUSES = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    private static final List<String> SECOND_NAMES = Arrays.asList("Иванов", "Петров", "Сидоров", "Козлов");

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void initializeData() {
        Random random = new Random();
        List<ClientType> clientTypes = List.of(ClientType.JURIDICAL, ClientType.PHYSICAL);

        for (int i = 0; i < 6; i++) {
            Client client = new Client();
            client.setClientName(getRandomNames());
            client.setType(getRandomClientType(random));
            client.setAdded(getRandomDate(random));
            client.setAddresses(new ArrayList<Address>());

            for (int j = 0; j < 2; j++) {
                Address address = new Address();
                address.setIp("192.168." + random.nextInt(256) + "." + random.nextInt(256));
                address.setMac(generateRandomMacAddress());
                address.setModel("Model " + (j + 1));
                address.setAddress(getRandomAddress());
                address.setClient(client);
                client.getAddresses().add(address);
            }

            entityManager.persist(client);
        }
    }

    private String generateRandomMacAddress() {
        Random random = new Random();
        StringBuilder macAddress = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            macAddress.append(random.nextInt(55));
            if (i < 5) {
                macAddress.append("-");
            }
        }

        return macAddress.toString().toUpperCase();
    }

    private ClientType getRandomClientType(Random random) {
        List<ClientType> clientTypes = List.of(ClientType.JURIDICAL, ClientType.PHYSICAL);
        return clientTypes.get(random.nextInt(clientTypes.size()));
    }

    private LocalDate getRandomDate(Random random) {
        int minYear = 2015;
        int maxYear = 2022;
        int year = random.nextInt(maxYear - minYear + 1) + minYear;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        return LocalDate.of(year, month, day);
    }

    public static String getRandomAddress() {
        Random random = new Random();
        String city = CITIES.get(random.nextInt(CITIES.size()));
        String street = STREETS.get(random.nextInt(STREETS.size()));
        String house = HOUSES.get(random.nextInt(HOUSES.size()));

        return city + ", " + street + ", " + house;
    }

    public static String getRandomNames() {
        Random random = new Random();
        String secondName = SECOND_NAMES.get(random.nextInt(SECOND_NAMES.size()));
        return secondName + "  Иван Петрович";
    }
}
