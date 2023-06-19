package com.example.labservlet.models.entitys;

import com.example.labservlet.models.enums.ClientType;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @Column(name = "client_name")
    @NotBlank(message = "Имя клиента не может быть пустым")
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[а-яА-Я, .-]+", message = "Имя клиента может содержать только русские буквы, знаки '-', ',', '.'")
    private String clientName;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type")
    private ClientType type;
    @Column(name = "added")
    @Past(message = "Дата должна быть меньше текущей")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate added;
    @Valid
    @JoinColumn(name = "clientId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Address> addresses;

    public Client() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String client_name) {
        this.clientName = client_name;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    /* public List<ClientType> getClientTypes() {
        return Arrays.asList(ClientType.values());
    }

    public String clientTypeString() {
        return type.getDisplayName();
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) && Objects.equals(clientName, client.clientName) && type == client.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientName, type);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", type=" + type +
                ", added=" + added +
                ", addresses=" + addresses +
                '}';
    }
}
