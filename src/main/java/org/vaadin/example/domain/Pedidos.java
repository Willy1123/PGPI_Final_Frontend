package org.vaadin.example.domain;

import java.util.List;

public class Pedidos {

    private String id;
    private String nameCampaign;
    private String dir;
    private List<Tuple> items;
    private String postal;
    private String zone;
    private String agency;
    // 0 estandar, 1 urgente
    private String type;
    private String pedidoDate;
    private String entregaDate;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCampaign() {
        return nameCampaign;
    }

    public void setNameCampaign(String nameCampaign) {
        this.nameCampaign = nameCampaign;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public List<Tuple> getItems() {
        return items;
    }

    public void setItems(List<Tuple> items) {
        this.items = items;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPedidoDate() {
        return pedidoDate;
    }

    public void setPedidoDate(String pedidoDate) {
        this.pedidoDate = pedidoDate;
    }

    public String getEntregaDate() {
        return entregaDate;
    }

    public void setEntregaDate(String entregaDate) {
        this.entregaDate = entregaDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
