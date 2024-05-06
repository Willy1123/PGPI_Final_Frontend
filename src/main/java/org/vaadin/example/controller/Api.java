package org.vaadin.example.controller;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.vaadin.example.domain.Pedidos;
import org.vaadin.example.domain.Products;
import org.vaadin.example.domain.ndData;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Component
public class Api {
    private static final String URL = "http://localhost:8081/";

    // Población
    public String getndData() throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "ndData";
        java.net.URL url = new URL(urlCompleta);
        // Creamos un HTTPRequest con la URL
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).GET().build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

    public String getndDataMsC() throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "ndData/MsCode";
        java.net.URL url = new URL(urlCompleta);
        // Creamos un HTTPRequest con la URL
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).GET().build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

    public String editndData(ndData ndData) throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "ndData/" + ndData.getID();

        // Convertimos el objeto ndData a JSON
        Gson gson = new Gson();
        String json = gson.toJson(ndData);

        // Creamos un HTTPRequest con la URL y el JSON
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlCompleta))
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();

        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String postndData(ndData ndData) throws Exception{
        // Se crea la URL de la API
        String urlCompleta = URL + "ndData";

        // Convertimos el objeto ndData a JSON
        Gson gson = new Gson();
        UUID id = UUID.randomUUID();
        ndData.setID(id.toString());
        String json = gson.toJson(ndData);

        // Creamos un HTTPRequest con la URL y el JSON
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    // Productos
    public String getProducts() throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "Products";
        java.net.URL url = new URL(urlCompleta);
        // Creamos un HTTPRequest con la URL
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).GET().build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

    public String getProductOrder() throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "Products/Name";
        java.net.URL url = new URL(urlCompleta);
        // Creamos un HTTPRequest con la URL
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).GET().build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

    public String editProduct(Products product) throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "Products/" + product.getId();

        // Convertimos el objeto ndData a JSON
        Gson gson = new Gson();
        String json = gson.toJson(product);

        // Creamos un HTTPRequest con la URL y el JSON
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlCompleta))
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();

        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String postProduct(Products product) throws Exception{
        // Se crea la URL de la API
        String urlCompleta = URL + "Products";

        // Convertimos el objeto ndData a JSON
        Gson gson = new Gson();
        UUID id = UUID.randomUUID();
        product.setId(id.toString());
        String json = gson.toJson(product);

        // Creamos un HTTPRequest con la URL y el JSON
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    // Pedidos
    public String getPedidos() throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "Pedidos";
        java.net.URL url = new URL(urlCompleta);
        // Creamos un HTTPRequest con la URL
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).GET().build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

    public String getPedidosOrder() throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "Pedidos/New";
        java.net.URL url = new URL(urlCompleta);
        // Creamos un HTTPRequest con la URL
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).GET().build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

    public String postPedido(Pedidos pedidos) throws Exception{
        // Se crea la URL de la API
        String urlCompleta = URL + "Pedidos";

        // Convertimos el objeto ndData a JSON
        Gson gson = new Gson();
        UUID id = UUID.randomUUID();
        pedidos.setId(id.toString());
        String json = gson.toJson(pedidos);

        // Creamos un HTTPRequest con la URL y el JSON
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlCompleta)).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();
        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String editPedido(Pedidos pedidos) throws Exception {
        // Se crea la URL de la API
        String urlCompleta = URL + "Pedido/" + pedidos.getId();

        // Convertimos el objeto ndData a JSON
        Gson gson = new Gson();
        String json = gson.toJson(pedidos);

        // Creamos un HTTPRequest con la URL y el JSON
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlCompleta))
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();

        // Recibimos la respuesta de la API y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
