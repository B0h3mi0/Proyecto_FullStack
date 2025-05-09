package com.veterinaria.veterinaria.service;
import java.util.List;

import com.veterinaria.veterinaria.api.request.FacturaUpdateRequest;
import com.veterinaria.veterinaria.model.Factura;
import java.util.Optional;

public interface FacturaService {

    List<Factura> getAllFacturas();
    Optional<Factura> getFacturaById(Long id);
    Factura createFactura(Factura factura);
    Factura updateFactura(Long id, FacturaUpdateRequest updateRequest);
    void deleteFacturaById(Long id);

}

