package com.example.labservlet.models.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ip")
    @NotBlank
    @Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", message = "Неверный формат IP адреса")
    private  String ip;
    @Column(name = "mac")
    @Pattern(regexp = "^[0-9A-Fa-f]{1,2}(-[0-9A-Fa-f]{1,2}){5}$", message = "Неверный формат MAC адреса")
    @NotBlank

    private  String mac;
    @Column(name = "model")
    @Size(min = 1, max = 100)
    private  String model;
    @Column(name = "address")
    @Size(min = 1, max = 200)
    private String address;

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(id, address1.id) && Objects.equals(ip, address1.ip) && Objects.equals(mac, address1.mac) && Objects.equals(model, address1.model) && Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ip, mac, model, address);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                ", model='" + model + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
