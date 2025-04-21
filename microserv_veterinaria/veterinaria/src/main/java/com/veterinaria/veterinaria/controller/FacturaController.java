package com.veterinaria.veterinaria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.veterinaria.api.request.FacturaUpdateRequest;
import com.veterinaria.veterinaria.model.Factura;
import com.veterinaria.veterinaria.service.FacturaService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class FacturaController {
    // METODOS GetMapping ////////////////////
    private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);

    @Autowired
    private FacturaService facturaService;

    @GetMapping("/all")
    public ResponseEntity<List<Factura>> getAllFacturas(){
        logger.info("Getting all Facturas");
        List<Factura> facturas = facturaService.getAllFacturas();
        logger.info("Found {} facturas", facturas.size());
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        logger.info("Getting a factura by ID: {}", id);
        Optional<Factura> factura = facturaService.getFacturaById(id);
        return factura.map(value -> {
            logger.info("Factura found by ID: {}", id);
            return new ResponseEntity<>(value, HttpStatus.OK);
        })
        .orElseGet(() -> {
            logger.info("Factura not found by ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }
    

    @PostMapping()
    public ResponseEntity<Factura> creaFactura(@RequestBody Factura factura) {
        logger.info("Creating a new factura with request: {}", factura);
        Factura savedFactura = facturaService.createFactura(factura);
        logger.info("Factura created successfully. factura ID: {}", savedFactura.getId_factura());
        return new ResponseEntity<>(savedFactura, HttpStatus.CREATED);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Factura> patchFactura(@PathVariable Long id, @RequestBody FacturaUpdateRequest updateRequest) {
        logger.info("Updating a factura with ID: {} and request: {}", id, updateRequest);
        Factura updatedFactura = facturaService.updateFactura(id, updateRequest);
        logger.info("Factura updated successfully. User ID: {}", updatedFactura.getId_factura());
        return new ResponseEntity<>(updatedFactura, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id){
    logger.info("Deleting a factura with ID: {}", id);
    facturaService.deleteFacturaById(id);
    logger.info("Factura deleted successfully. Factura ID: {}", id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
