package com.intecap.tareaFinal.controller;

import com.intecap.tareaFinal.model.ArticuloEntity;
import com.intecap.tareaFinal.response.ArticuloResponseRest;
import com.intecap.tareaFinal.service.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") //IMPORTANTE, aqui se da acceso a una whitelist, si no se coloca, el cliente en React da error de axios network
@RestController
@RequestMapping("api/tareafinal")
public class ArticuloController {

    @Autowired
    private IArticuloService articuloService;

    @GetMapping("/articulos")
    public ResponseEntity<ArticuloResponseRest> darArticulo(){
        return articuloService.buscarArticulo();
    }

    @GetMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest> darArticuloId(@PathVariable Long id){
        return articuloService.buscarArticuloId(id);
    }

    @PostMapping("/articulos")
    public ResponseEntity<ArticuloResponseRest> crearArticulo(@RequestBody ArticuloEntity request){
        return articuloService.crearArticulo(request);
    }

    @PutMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(@RequestBody ArticuloEntity request, @PathVariable Long id){
        return articuloService.actualizarArticulo(request,id);
    }

    @DeleteMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(@PathVariable Long id){
        return articuloService.eliminarArticulo(id);
    }

}