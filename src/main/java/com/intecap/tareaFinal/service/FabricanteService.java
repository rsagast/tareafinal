package com.intecap.tareaFinal.service;

import com.intecap.tareaFinal.model.FabricanteEntity;
import com.intecap.tareaFinal.model.dao.IFabricanteDAO;
import com.intecap.tareaFinal.response.FabricanteResponseRest;
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
class FabricanteService implements IFabricanteService{

    private static final Logger log = Logger.getLogger(FabricanteService.class.getName());

    @Autowired
    private IFabricanteDAO fabricanteDAO;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarFabricante() {
        log.info("Inicio - buscarFabricante()");
        FabricanteResponseRest response = new FabricanteResponseRest();
        try{
            List<FabricanteEntity> fabricanteEntityList = (List<FabricanteEntity>) fabricanteDAO.findAll();
            response.getFabricanteResponse().setFabricanteEntityList(fabricanteEntityList);
            response.setMetadata("buscarFabricante()","200","Resultado exitoso");
        } catch (Exception ex) {
            log.severe("Error - buscarFabricante(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("buscarFabricante()","500","Resultado erroneo");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarFabricanteId(Long id) {
        log.info("Inicio - buscarFabricanteId()");
        FabricanteResponseRest response = new FabricanteResponseRest();
        try{
            List<FabricanteEntity> fabricanteEntityList = new ArrayList<>();
            Optional<FabricanteEntity> fabricanteEntity = fabricanteDAO.findById(id);
            if (fabricanteEntity.isPresent()) {
                fabricanteEntityList.add(fabricanteEntity.get());
                response.getFabricanteResponse().setFabricanteEntityList(fabricanteEntityList);
                response.setMetadata("buscarFabricanteId()","200","Resultado exitoso");
            } else {
                response.setMetadata("buscarFabricanteId()","404","Resultado no encontrado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.severe("Error - buscarFabricanteId(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("buscarFabricanteId()","500","Resultado erroneo");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> crearFabricante(FabricanteEntity fabricanteEntity) {
        log.info("Inicio - crearFabricante()");
        FabricanteResponseRest response = new FabricanteResponseRest();
        try{
            List<FabricanteEntity> fabricanteEntityList = new ArrayList<>();
            FabricanteEntity fabricanteEntityNuevo = fabricanteDAO.save(fabricanteEntity);
            if (fabricanteEntityNuevo != null) {
                fabricanteEntityList.add(fabricanteEntityNuevo);
                response.getFabricanteResponse().setFabricanteEntityList(fabricanteEntityList);
                response.setMetadata("crearFabricante()","200","Resultado exitoso");
            } else {
                response.setMetadata("crearFabricante()","404","Resultado no creado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.severe("Error - crearFabricante(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("crearFabricante()","500","Resultado erroneo");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(FabricanteEntity fabricanteEntity, Long id) {
        log.info("Inicio - actualizarFabricante()");
        FabricanteResponseRest response = new FabricanteResponseRest();
        try{
            List<FabricanteEntity> fabricanteEntityList = new ArrayList<>();
            Optional<FabricanteEntity> fabricanteEntityBuscado = fabricanteDAO.findById(id);
            if (fabricanteEntityBuscado.isPresent()) {
                fabricanteEntityBuscado.get().setNombre(fabricanteEntity.getNombre());
                FabricanteEntity fabricanteEntityNuevo = fabricanteDAO.save(fabricanteEntityBuscado.get());
                if (fabricanteEntityNuevo != null) {
                    fabricanteEntityList.add(fabricanteEntityNuevo);
                    response.getFabricanteResponse().setFabricanteEntityList(fabricanteEntityList);
                    response.setMetadata("actualizarFabricante()","200","Resultado exitoso");
                } else {
                    response.setMetadata("actualizarFabricante()","404","Resultado no creado");
                    return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
                }
            } else {
                response.setMetadata("actualizarFabricante()","404","Resultado no encontrado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.severe("Error - actualizarFabricante(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("buscarFabricanteId()","500","Resultado erroneo");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id) {
        log.info("Inicio - eliminarFabricante()");
        FabricanteResponseRest response = new FabricanteResponseRest();
        try{
            Optional<FabricanteEntity> fabricanteEntityBuscado = fabricanteDAO.findById(id);
            if (fabricanteEntityBuscado.isPresent()) {
                fabricanteDAO.delete(fabricanteEntityBuscado.get());
                response.setMetadata("eliminarFabricante()","200","Resultado exitoso");
            } else {
                response.setMetadata("eliminarFabricante()","404","Resultado no encontrado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.severe("Error - eliminarFabricante(): " + ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("eliminarFabricante()","500","Resultado erroneo");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

}