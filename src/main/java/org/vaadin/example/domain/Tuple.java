package org.vaadin.example.domain;

import com.google.gson.annotations.SerializedName;

public class Tuple {

    @SerializedName("productName")
    private String productName;
    @SerializedName("quantity")
    private int quantity;

    public Tuple() {
    }

    public Tuple(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
