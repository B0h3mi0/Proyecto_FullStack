package com.veterinaria.veterinaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.veterinaria.veterinaria.repository.FacturaRepository;
import com.veterinaria.veterinaria.model.Factura;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTest {
    @InjectMocks
    private FacturaServiceImpl facturaServicio;

    @Mock
    private FacturaRepository facturaRepositoryMock;

    @Test
    public void guardarFactura(){

        Factura factura = new Factura();
        factura.setServicio("cirugia");

        when(facturaRepositoryMock.save(any())).thenReturn(factura);

        Factura resultado = facturaServicio.createFactura(factura);

        assertEquals("cirugia", resultado.getServicio());

    }
}