package com.intecap.tareaFinal.service;

import com.intecap.tareaFinal.model.ArticuloEntity;
import com.intecap.tareaFinal.model.FabricanteEntity;
import com.intecap.tareaFinal.model.dao.IArticuloDAO;
import com.intecap.tareaFinal.response.ArticuloResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ArticuloService implements IArticuloService{

    private static final Logger log = Logger.getLogger(ArticuloService.class.getName());

    @Autowired
    private IArticuloDAO articuloDAO;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticulo() {
        log.info("Inicio - buscarArticulo()");
        ArticuloResponseRest response = new ArticuloResponseRest();
        try{
            List<ArticuloEntity> articuloEntityList = (List<ArticuloEntity>) articuloDAO.findAll();
            response.getArticuloResponse().setArticuloEntityList(articuloEntityList);
            response.setMetadata("buscarArticulo()","200","Resultado exitoso");
        } catch (Exception ex) {
            log.severe("Error - buscarArticulo(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("buscarArticulo()","500","Resultado erroneo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticuloId(Long id) {
        log.info("Inicio - buscarArticuloId()");
        ArticuloResponseRest response = new ArticuloResponseRest();
        try{
            List<ArticuloEntity> articuloEntityList = new ArrayList<>();
            Optional<ArticuloEntity> articuloEntity = articuloDAO.findById(id);
            if (articuloEntity.isPresent()) {
                articuloEntityList.add(articuloEntity.get());
                response.getArticuloResponse().setArticuloEntityList(articuloEntityList);
                response.setMetadata("buscarArticuloId()","200","Resultado exitoso");
            } else {
                response.setMetadata("buscarArticuloId()","404","Resultado no encontrado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.severe("Error - buscarArticuloId(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("buscarArticuloId()","500","Resultado erroneo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> crearArticulo(ArticuloEntity articuloEntity) {
        log.info("Inicio - crearArticulo()");
        ArticuloResponseRest response = new ArticuloResponseRest();
        try{
            List<ArticuloEntity> articuloEntityList = new ArrayList<>();
            ArticuloEntity articuloEntityNuevo = articuloDAO.save(articuloEntity);
            if (articuloEntityNuevo != null) {
                articuloEntityList.add(articuloEntityNuevo);
                response.getArticuloResponse().setArticuloEntityList(articuloEntityList);
                response.setMetadata("crearArticulo()","200","Resultado exitoso");
            } else {
                response.setMetadata("crearArticulo()","404","Resultado no creado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.severe("Error - crearArticulo(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("crearArticulo()","500","Resultado erroneo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(ArticuloEntity articuloEntity, Long id) {
        log.info("Inicio - actualizarArticulo()");
        ArticuloResponseRest response = new ArticuloResponseRest();
        try{
            List<ArticuloEntity> articuloEntityList = new ArrayList<>();
            Optional<ArticuloEntity> articuloEntityBuscado = articuloDAO.findById(id);
            if (articuloEntityBuscado.isPresent()) {
                FabricanteEntity fabricanteEntityNuevo = new FabricanteEntity();
                fabricanteEntityNuevo.setId(articuloEntity.getFabricanteEntity().getId());
                articuloEntityBuscado.get().setNombre(articuloEntity.getNombre());
                articuloEntityBuscado.get().setPrecio(articuloEntity.getPrecio());
                articuloEntityBuscado.get().setFabricanteEntity(fabricanteEntityNuevo);
                ArticuloEntity articuloEntityNuevo = articuloDAO.save(articuloEntityBuscado.get());
                if (articuloEntityNuevo != null) {
                    articuloEntityList.add(articuloEntityNuevo);
                    response.getArticuloResponse().setArticuloEntityList(articuloEntityList);
                    response.setMetadata("actualizarArticulo()","200","Resultado exitoso");
                } else {
                    response.setMetadata("actualizarArticulo()","404","Resultado no creado");
                    return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
                }
            } else {
                response.setMetadata("actualizarArticulo()","404","Resultado no encontrado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.severe("Error - actualizarArticulo(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("actualizarArticulo()","500","Resultado erroneo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long id) {
        log.info("Inicio - eliminarArticulo()");
        ArticuloResponseRest response = new ArticuloResponseRest();
        try{
            Optional<ArticuloEntity> articuloEntityBuscado = articuloDAO.findById(id);
            if (articuloEntityBuscado.isPresent()) {
                articuloDAO.delete(articuloEntityBuscado.get());
                response.setMetadata("eliminarArticulo()","200","Resultado exitoso");
            } else {
                response.setMetadata("eliminarArticulo()","404","Resultado no encontrado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.severe("Error - eliminarArticulo(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("eliminarArticulo()","500","Resultado erroneo");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

}