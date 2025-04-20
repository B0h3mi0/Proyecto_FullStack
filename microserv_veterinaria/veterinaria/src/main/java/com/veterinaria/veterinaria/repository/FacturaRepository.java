package com.veterinaria.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.veterinaria.veterinaria.model.Factura;
public interface FacturaRepository extends JpaRepository <Factura, Long>{
    
}
