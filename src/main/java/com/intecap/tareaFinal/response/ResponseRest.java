package com.intecap.tareaFinal.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
    private ArrayList<HashMap<String,String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String tipo, String codigo, String valor) {
        HashMap<String,String> dato = new HashMap<>();
        dato.put("tipo",tipo);
        dato.put("codigo",codigo);
        dato.put("valor",valor);
        this.metadata.add(dato);
    }
}
