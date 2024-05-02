package org.vaadin.example;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class ndData {
    //Creamos los atributos de la clase ndData
    @SerializedName("id")
    private String id;
    @SerializedName("msCode")
    private String msCode;

    @SerializedName("year")
    private String year;

    @SerializedName("estCode")
    private String estCode;

    @SerializedName("estimate")
    private Float estimate;

    @SerializedName("se")
    private Float se;

    @SerializedName("lowerCIB")
    private Float lowerCIB;

    @SerializedName("upperCIB")
    private Float upperCIB;

    @SerializedName("flag")
    private String flag;
    //Creamos los constructores
    public ndData() {
    }
    public ndData(String ID, String msCode, String year, String estCode, Float estimate, Float se, Float lowerCIB, Float upperCIB, String flag) {
        this.id = ID;
        this.msCode = msCode;
        this.year = year;
        this.estCode = estCode;
        this.estimate = estimate;
        this.se = se;
        this.lowerCIB = lowerCIB;
        this.upperCIB = upperCIB;
        this.flag = flag;
    }
    //Creamos los getters y setters
    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
    public String getMsCode() {
        return msCode;
    }
    public void setMsCode(String msCode) {
        this.msCode = msCode;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getEstCode() {
        return estCode;
    }

    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }

    public Float getEstimate() {
        return estimate;
    }

    public void setEstimate(Float estimate) {
        this.estimate = estimate;
    }

    public Float getSe() {
        return se;
    }

    public void setSe(Float se) {
        this.se = se;
    }

    public Float getLowerCIB() {
        return lowerCIB;
    }

    public void setLowerCIB(Float lowerCIB) {
        this.lowerCIB = lowerCIB;
    }

    public Float getUpperCIB() {
        return upperCIB;
    }

    public void setUpperCIB(Float upperCIB) {
        this.upperCIB = upperCIB;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }



}