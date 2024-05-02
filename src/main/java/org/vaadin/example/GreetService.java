package org.vaadin.example;

import java.io.Serializable;
import java.util.List;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class GreetService implements Serializable {
    Api api = new Api();
    //método getndData que devuelve una lista de ndData a partir del JSON que recive de la API
    public  List<ndData> Getdata() throws Exception {
        Gson gson = new Gson();
        List<ndData> ndData = gson.fromJson(api.getndData(), new TypeToken<List<ndData>>() {}.getType());
        return ndData;
    }
    //méttodo editndData que recibe un objeto de tipo ndData y lo envía a la API para su edición
    public String editData(ndData ndData) throws Exception {
        String respuesta = api.editndData(ndData);
        return respuesta;
    }
    //método postData que recibe un objeto de tipo ndData y lo envía a la API para su creación
    public String postData(ndData ndData) throws Exception {
        String respuesta = api.postndData(ndData);
        return respuesta;
    }
    //método GetDataMsC que devuelve una lista de ndData a partir del JSON que recive de la API
    public List<ndData> GetDataMsC() throws Exception {
        Gson gson = new Gson();
        List<ndData> ndData = gson.fromJson(api.getndDataMsC(), new TypeToken<List<ndData>>() {}.getType());

        return ndData;
    }
}