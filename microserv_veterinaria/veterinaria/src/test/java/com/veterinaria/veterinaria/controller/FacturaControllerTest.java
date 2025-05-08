package com.veterinaria.veterinaria.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.veterinaria.veterinaria.api.request.FacturaUpdateRequest;
import com.veterinaria.veterinaria.model.Factura;
import com.veterinaria.veterinaria.service.FacturaService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaControllerTest {

    @Mock
    private FacturaService facturaService;

    @InjectMocks
    private FacturaController facturaController;

    private Factura factura;

    @BeforeEach
    void setUp() {
        factura = new Factura();
        factura.setId_factura(1L);
        factura.setServicio("testServicio");
        factura.setNom_mascota("Firualis");
        factura.setCosto_asociado(13500);
        factura.setChip(true);
        factura.setNom_dueño("TestDueño");
        factura.setNom_veterinario("Rodolfo");
        factura.setTipo_mascota("Can-Pastor Aleman");
    }

    @Test
    void createFactura() {
        // Arrange
        when(facturaService.createFactura(any(Factura.class))).thenReturn(factura);

        EntityModel<Factura> response = facturaController.createFactura(factura);

        assertNotNull(response);
        assertEquals(factura.getId_factura(), response.getContent().getId_factura());
        assertTrue(response.hasLink("self"));
    }

    @Test
    void deleteFactura() {
        // Arrange
        Long id = 1L;
        doNothing().when(facturaService).deleteFacturaById(id);

        // Act
        facturaController.deleteFactura(id);

        // Assert
        verify(facturaService).deleteFacturaById(id);
}
}
