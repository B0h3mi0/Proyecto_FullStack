package com.veterinaria.veterinaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.veterinaria.model.Factura;
import com.veterinaria.veterinaria.repository.FacturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService{
    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }
    
    @Override
    public Factura createFactura(Factura factura){
        return facturaRepository.save(factura);
    }

    @Override
    public Factura updateFactura(Long id, Factura factura){
        if(facturaRepository.existsById(id)){
            factura.setId_factura(id);
            return facturaRepository.save(factura);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteFactura(Long id){
        facturaRepository.deleteById(id);
    }
}
