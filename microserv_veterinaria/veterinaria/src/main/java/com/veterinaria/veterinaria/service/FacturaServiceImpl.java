package com.veterinaria.veterinaria.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.veterinaria.model.Factura;
import com.veterinaria.veterinaria.repository.FacturaRepository;

import lombok.RequiredArgsConstructor;

import com.veterinaria.veterinaria.api.request.FacturaUpdateRequest;
import com.veterinaria.veterinaria.exceptionhandler.DatabaseTransactionException;
import com.veterinaria.veterinaria.exceptionhandler.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService{
    @Autowired
    private FacturaRepository facturaRepository;
    private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);


    @Override
    public List<Factura> getAllFacturas() {
        logger.info("Finding all facturas - method getAllFacturas");
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> getFacturaById(Long id) {
        logger.info("Finding factura by ID: {} - method getFacturaById", id);
        return facturaRepository.findById(id);
    }
    
    @Override
    public Factura createFactura(Factura facturaToSave){
        logger.info("Creando factura con request: {} - metodo saveFactura",facturaToSave);
        try {
            Factura savedFactura = facturaRepository.save(facturaToSave);
            logger.info("Factura creado satisfactoriamente. Factura ID: {} - metodo createFactura",savedFactura.getId_factura());
            return savedFactura;
        } catch (Exception e) {
            logger.error("Error creando factura - metodo createFactura",e);
            throw new DatabaseTransactionException("Error creando factura",e);
        }
    }

    @Override
    public Factura updateFactura(Long id, FacturaUpdateRequest updateRequest){
        logger.info("Updating factura with ID: {} and request: {} - method updatefactura", id, updateRequest);
        Factura factura = facturaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("factura not found"));
            
        if (updateRequest.getServicio() != null) {
            logger.info("Updating Servicio to: {} - method updateFactura", updateRequest.getServicio());
            factura.setServicio(updateRequest.getServicio());
        }
        if (updateRequest.getCosto_asociado() != 0) {
            logger.info("Updating Costo_asociado to: {} - method updateFactura", updateRequest.getCosto_asociado());
            factura.setCosto_asociado(updateRequest.getCosto_asociado());
        }
        if (updateRequest.getNom_veterinario() != null) {
            logger.info("Updating Nom_veterinario to: {} - method updateFactura", updateRequest.getNom_veterinario());
            factura.setNom_veterinario(updateRequest.getNom_veterinario());
        }
        if (updateRequest.getNom_mascota() != null) {
            logger.info("Updating Nom_mascota to: {} - method updateFactura", updateRequest.getNom_mascota());
            factura.setNom_mascota(updateRequest.getNom_mascota());
        }
        if (updateRequest.getNom_dueño() != null) {
            logger.info("Updating Nom_mascota to: {} - method updateFactura", updateRequest.getNom_dueño());
            factura.setNom_dueño(updateRequest.getNom_dueño());
        }
        if (updateRequest.getTipo_mascota() != null) {
            logger.info("Updating Nom_mascota to: {} - method updateFactura", updateRequest.getTipo_mascota());
            factura.setTipo_mascota(updateRequest.getTipo_mascota());
        }
        /* if (updateRequest.getClass() != null) {
            logger.info("Updating Chip to: {} - method updateFactura", updateRequest.getClass());
        } */
        
        logger.info("Saving factura - method updateFactura");
        Factura updatedFactura = facturaRepository.save(factura);
        logger.info("Factura updated successfully. Factura ID: {}", updatedFactura.getId_factura());
        return updatedFactura;
    }

    @Override
    public void deleteFacturaById(Long id){
        logger.info("Deleting factura by ID: {} - method deleteFacturaById", id);
        Optional<Factura> factura = facturaRepository.findById(id);
        if (factura.isEmpty()) {
            logger.info("Factura not found - method deleteFacturaById");
            throw new ResourceNotFoundException("Factura not found");
        }
        logger.info("Deleting Factura - method deleteFacturaById");
        facturaRepository.deleteById(id);
        logger.info("Factura deleted successfully. Factura ID: {} - method deleteFacturaById", id);
    }
}
