package com.intecap.tareaFinal.response;

import com.intecap.tareaFinal.model.FabricanteEntity;

import java.util.List;

public class FabricanteResponse {
    private List<FabricanteEntity> fabricanteEntityList;

    public List<FabricanteEntity> getFabricanteEntityList() {
        return fabricanteEntityList;
    }

    public void setFabricanteEntityList(List<FabricanteEntity> fabricanteEntityList) {
        this.fabricanteEntityList = fabricanteEntityList;
    }
}
