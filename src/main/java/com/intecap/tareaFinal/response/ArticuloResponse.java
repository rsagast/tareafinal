package com.intecap.tareaFinal.response;

import com.intecap.tareaFinal.model.ArticuloEntity;

import java.util.List;

public class ArticuloResponse {
    List<ArticuloEntity> articuloEntityList;

    public List<ArticuloEntity> getArticuloEntityList() {
        return articuloEntityList;
    }

    public void setArticuloEntityList(List<ArticuloEntity> articuloEntityList) {
        this.articuloEntityList = articuloEntityList;
    }
}