package org.vaadin.example;

import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;

public class StringToFloatConverter implements Converter<String, Float> {

    private String MensajeError;

    public StringToFloatConverter(String mensajeError) {
        this.MensajeError = mensajeError;
    }

    //En este método se convierte el String a Float
    @Override
    public Result<Float> convertToModel(String fieldValue, ValueContext context) {
        try {
            // Intenta convertir el valor de String a Float
            return Result.ok(Float.parseFloat(fieldValue));
        } catch (NumberFormatException e) {
            //Si no puede convertir el valor, devuelve un error
            return Result.error(MensajeError);
        }
    }
    //En este método se convierte el Float a String
    @Override
    public String convertToPresentation(Float modelValue, ValueContext context) {
        return modelValue == null ? "" : modelValue.toString();
    }
}