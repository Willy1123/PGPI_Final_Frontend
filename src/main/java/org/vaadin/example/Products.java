package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Products {
    //Creamos los atributos de la clase ndData
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    @SerializedName("stock")
    private Float stock;

    @SerializedName("description")
    private String description;

    @SerializedName("expiration_date")
    private String expiration_date;

    //Creamos los constructores
    public Products() {
    }

    public Products(String id, String name, Float stock, String description, String expiration_date) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.expiration_date = expiration_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getStock() {
        return stock;
    }

    public void setStock(Float stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

}