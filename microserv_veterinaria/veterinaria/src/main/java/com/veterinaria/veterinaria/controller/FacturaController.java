package com.veterinaria.veterinaria.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    /* @GetMapping("/all")
    public ResponseEntity<List<Factura>> getAllFacturas(){
        logger.info("Getting all Facturas");
        List<Factura> facturas = facturaService.getAllFacturas();
        logger.info("Found {} facturas", facturas.size());
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    } */
    @GetMapping
    public CollectionModel<EntityModel<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaService.getAllFacturas();
        logger.info("GET /facturas");
        logger.info("Retornando todas las facturas");
        List<EntityModel<Factura>> facturasResources = facturas.stream()
            .map( factura -> EntityModel.of(factura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturaById(factura.getId_factura())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas());
        CollectionModel<EntityModel<Factura>> resources = CollectionModel.of(facturasResources, linkTo.withRel("facturas"));

        return resources;
    }

    /* @GetMapping("/{id}")
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
    } */
    @GetMapping("/{id}")
    public EntityModel<Factura> getFacturaById(@PathVariable Long id) {
        Optional<Factura> factura = facturaService.getFacturaById(id);

        if (factura.isPresent()) {
            return EntityModel.of(factura.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));
        } else {
            throw new FacturaNotFoundException("Factura not found with id: " + id);
        }
    }

    /* @PostMapping()
    public ResponseEntity<Factura> creaFactura(@RequestBody Factura factura) {
        logger.info("Creating a new factura with request: {}", factura);
        Factura savedFactura = facturaService.createFactura(factura);
        logger.info("Factura created successfully. factura ID: {}", savedFactura.getId_factura());
        return new ResponseEntity<>(savedFactura, HttpStatus.CREATED);
    } */
    
    @PostMapping
    public ResponseEntity<EntityModel<Factura>> createFactura(@Validated @RequestBody Factura factura) {
        Factura createdFactura = facturaService.createFactura(factura);
            EntityModel<Factura> model = EntityModel.of(createdFactura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturaById(createdFactura.getId_factura())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    /* @PatchMapping("/{id}")
    public ResponseEntity<Factura> patchFactura(@PathVariable Long id, @RequestBody FacturaUpdateRequest updateRequest) {
        logger.info("Updating a factura with ID: {} and request: {}", id, updateRequest);
        Factura updatedFactura = facturaService.updateFactura(id, updateRequest);
        logger.info("Factura updated successfully. Factura ID: {}", updatedFactura.getId_factura());
        return new ResponseEntity<>(updatedFactura, HttpStatus.OK);
    } */

    @PutMapping("/{id}")
    public EntityModel<Factura> updateFactura(@PathVariable Long id, @RequestBody FacturaUpdateRequest updateRequest) {
        Factura updatedFactura = facturaService.updateFactura(id, updateRequest);
        return EntityModel.of(updatedFactura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));

    }

    /* @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id){
    logger.info("Deleting a factura with ID: {}", id);
    facturaService.deleteFacturaById(id);
    logger.info("Factura deleted successfully. Factura ID: {}", id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } */

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        facturaService.deleteFacturaById(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }
    
}
