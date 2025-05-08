package com.evento.evento.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.evento.evento.model.Evento;
import com.evento.evento.service.EventoService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventoControllerTest {

    @Mock
    private EventoService eventoService;

    @InjectMocks
    private EventoController eventoController;

    private Evento evento;

    @BeforeEach
    void setUp() {
        evento = new Evento();
        evento.setId_evento(1L);
        evento.setNom_evento("TestEventoMascotas");
        evento.setDescripcion("EventoMascotasTest");
        evento.setFecha_evento(LocalDate.of(2025,05,12));
        evento.setDireccion_evento("Avenida libertador bernardo brigadier 25445");
        evento.setCoordinador_evento("TestCoordinador");
    }

    @Test
    void createEvento() {
        // Arrange
        when(eventoService.createEvento(any(Evento.class))).thenReturn(evento);

        // Act
        ResponseEntity<EntityModel<Evento>> response = eventoController.createEvento(evento);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        EntityModel<Evento> savedEventoModel = response.getBody();
        assertNotNull(savedEventoModel);
        assertEquals(evento.getId_evento(), savedEventoModel.getContent().getId_evento());
        assertTrue(savedEventoModel.hasLink("self"));
        verify(eventoService).createEvento(any(Evento.class));
    }

    @Test
    void deleteEvento() {
        // Arrange
        doNothing().when(eventoService).deleteEventoById(1L);

        // Act
        ResponseEntity<Void> response = eventoController.deleteEvento(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventoService).deleteEventoById(1L);
    }
    
}
