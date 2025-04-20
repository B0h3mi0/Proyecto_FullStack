package com.veterinaria.veterinaria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.veterinaria.model.Factura;
import com.veterinaria.veterinaria.service.FacturaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {
    // METODOS GetMapping ////////////////////
    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public List<Factura> getAllFacturas(){
        return facturaService.getAllFacturas();
    }
        
    @GetMapping("/{id}")
    public Optional<Factura> getFacturaById(@PathVariable Long id) {
        return facturaService.getFacturaById(id);
    }

    @PostMapping
    public Factura creaFactura(@RequestBody Factura factura) {
        return facturaService.createFactura(factura);
    }
    
    @PutMapping("/{id}")
    public Factura updateFactura(@PathVariable Long id, @RequestBody Factura factura) {
        return facturaService.updateFactura(id, factura);
    }

    @DeleteMapping("/{id}")
    public void deleteFactura(@PathVariable Long id){
        facturaService.deleteFactura(id);
    }
    
}
