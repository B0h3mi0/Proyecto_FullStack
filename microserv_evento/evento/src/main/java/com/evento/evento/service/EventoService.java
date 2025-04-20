package com.evento.evento.service;

import java.util.List;
import com.evento.evento.model.Evento;
import java.util.Optional;

public interface EventoService {

    List<Evento> getAllEventos();
    Optional<Evento> getEventoById(Long id);
    Evento createEvento(Evento evento);
    Evento updateEvento(Long id,Evento evento);
    void deleteEvento(Long id);

}
