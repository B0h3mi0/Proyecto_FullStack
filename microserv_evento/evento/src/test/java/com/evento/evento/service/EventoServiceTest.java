package com.evento.evento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.evento.evento.repository.EventoRepository;
import com.evento.evento.model.Evento;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {
    @InjectMocks
    private EventoServiceImpl eventoServicio;

    @Mock
    private EventoRepository eventoRepositoryMock;

    @Test
    public void guardarEvento(){

        Evento evento = new Evento();
        evento.setNom_evento("Los piratas del caribe");

        when(eventoRepositoryMock.save(any())).thenReturn(evento);

        Evento resultado = eventoServicio.createEvento(evento);

        assertEquals("Los piratas del caribe", resultado.getNom_evento());

    }
}