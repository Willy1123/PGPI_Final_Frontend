package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import org.vaadin.example.controller.GreetService;
import org.vaadin.example.domain.Pedidos;
import org.vaadin.example.domain.Tuple;
import org.vaadin.example.domain.Products;
import org.vaadin.example.domain.ndData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CampaignForm extends VerticalLayout {
    private ComboBox<String> campaignNameComboBox;
    private ComboBox<String> productComboBox;
    private ComboBox<String> agency;
    private ComboBox<String> urgency;
    private TextField address;
    private TextField postal;
    private TextField zone;
    private TextField quantityField;
    private List<Tuple> tupleList;
    private Button addPickingButton;
    private Button saveButton;

    public CampaignForm(GreetService greetService) throws Exception {

        // Obtener todos los MsCode únicos de los datos
        List<String> uniquePoblationMscodes = greetService.GetDataMsC().stream()
                .map(ndData::getMsCode)
                .distinct()
                .collect(Collectors.toList());

        // ComboBox para seleccionar el nombre de la campaña
        campaignNameComboBox = new ComboBox<>("Campaign Name");
        campaignNameComboBox.setItems(uniquePoblationMscodes); // Rellenar con datos del backend

        // Campo para la dirección
        address = new TextField("Address");

        // Campo para el código postal
        postal = new TextField("Postal Code");

        zone = new TextField("Zone");

        String[] opciones = {"DHL", "UPS", "FedEx"};
        agency = new ComboBox<>("Agency");
        agency.setItems(opciones);

        String[] urgencia = {"True", "False"};
        urgency = new ComboBox<>("Urgency");
        urgency.setItems(urgencia);

        List<String> uniqueProductos = greetService.GetProductOrder().stream()
                .map(Products::getName)
                .distinct()
                .collect(Collectors.toList());
        // ComboBox para seleccionar el producto
        productComboBox = new ComboBox<>("Product");
        productComboBox.setItems(uniqueProductos); // Rellenar con datos del backend

        // Campo para la cantidad
        quantityField = new TextField("Quantity");

        // Lista temporal para almacenar elementos de Picking
        tupleList = new ArrayList<>();

        // Botón para añadir elementos a la lista temporal de Picking
        addPickingButton = new Button("Add Picking");
        addPickingButton.addClickListener(event -> {
            String selectedProductName = productComboBox.getValue();
            if (!selectedProductName.isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityField.getValue()); // Verificar la conversión
                    Tuple tuple = new Tuple();
                    Products selectedProduct = new Products(); // No es necesario crear un producto nuevo
                    //selectedProduct.setName(selectedProductName);
                    tuple.setProductName(selectedProductName);
                    tuple.setQuantity(quantity);
                    tupleList.add(tuple); // Añadir a la lista
                    Notification.show("Picking item added");
                } catch (NumberFormatException e) {
                    Notification.show("Invalid quantity. Please enter a number.");
                }
            } else {
                Notification.show("Please select a product.");
            }
        });


        // Botón para guardar la campaña
        saveButton = new Button("Save Campaign", e2 -> {
            Pedidos newPedido = new Pedidos();
            newPedido.setId(UUID.randomUUID().toString());
            newPedido.setNameCampaign(campaignNameComboBox.getValue());
            newPedido.setItems(tupleList);
            newPedido.setDir(address.getValue());
            newPedido.setPostal(postal.getValue());
            newPedido.setZone(zone.getValue());
            newPedido.setAgency(agency.getValue());
            newPedido.setType(urgency.getValue());
            newPedido.setState("pendiente");
            try {
                greetService.postPedido(newPedido);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });

        VerticalLayout vPedido = new VerticalLayout();
        HorizontalLayout hAddPedido = new HorizontalLayout();
        HorizontalLayout hLayoutPickList = new HorizontalLayout();
        HorizontalLayout hLayaoutSaveBtn = new HorizontalLayout();
        hAddPedido.add(campaignNameComboBox, address, postal, zone, agency, urgency);
        hLayoutPickList.add(productComboBox, quantityField);
        hLayaoutSaveBtn.add(addPickingButton, saveButton);
        add(hAddPedido, hLayoutPickList, hLayaoutSaveBtn);
        //add(vPedido);
    }

    public List<Tuple> getPickingList() {
        return tupleList;
    }

    public ComboBox<String> getCampaignNameComboBox() {
        return campaignNameComboBox;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
