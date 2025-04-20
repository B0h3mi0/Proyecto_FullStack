package com.evento.evento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evento.evento.model.Evento;
import com.evento.evento.repository.EventoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService{
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }
    
    @Override
    public Evento createEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    @Override
    public Evento updateEvento(Long id, Evento evento){
        if(eventoRepository.existsById(id)){
            evento.setId_evento(id);
            return eventoRepository.save(evento);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }
}