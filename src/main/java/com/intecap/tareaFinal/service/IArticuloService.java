package com.intecap.tareaFinal.service;

import com.intecap.tareaFinal.model.ArticuloEntity;
import com.intecap.tareaFinal.response.ArticuloResponseRest;
import org.springframework.http.ResponseEntity;

public interface IArticuloService {

    public ResponseEntity<ArticuloResponseRest> buscarArticulo();
    public ResponseEntity<ArticuloResponseRest> buscarArticuloId(Long id);
    public ResponseEntity<ArticuloResponseRest> crearArticulo(ArticuloEntity articuloEntity);
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(ArticuloEntity articuloEntity, Long id);
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long id);

}