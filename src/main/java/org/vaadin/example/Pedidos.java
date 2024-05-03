package org.vaadin.example;

import java.util.List;
import java.util.UUID;

public class Pedidos {

    private UUID id;
    private String nameCampain;
    private String address;
    private List<Picking> items;
    private String postal;
    private Integer units;
    private String zone;
    // 0 estandar, 1 urgente
    private Boolean type;
    private String state;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameCampain() {
        return nameCampain;
    }

    public void setNameCampain(String nameCampain) {
        this.nameCampain = nameCampain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Picking> getItems() {
        return items;
    }

    public void setItems(List<Picking> items) {
        this.items = items;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
