package com.evento.evento.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evento.evento.api.request.EventoUpdateRequest;
import com.evento.evento.exceptionhandler.DatabaseTransactionException;
import com.evento.evento.exceptionhandler.ResourceNotFoundException;
import com.evento.evento.model.Evento;
import com.evento.evento.repository.EventoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService{
    @Autowired
    private EventoRepository eventoRepository;
    private static final Logger logger = LoggerFactory.getLogger(EventoServiceImpl.class);

    @Override
    public List<Evento> getAllEventos() {
        logger.info("Finding all eventos - method getAllEventos");
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> getEventoById(Long id) {
        logger.info("Finding evento by ID: {} - method getEventoById", id);
        return eventoRepository.findById(id);
    }
    
    @Override
    public Evento createEvento(Evento eventoToSave){
        logger.info("Creando evento con request: {} - metodo saveUser",eventoToSave);
        try {
            Evento savedEvento = eventoRepository.save(eventoToSave);
            logger.info("Evento creado satisfactoriamente. Evento ID: {} - metodo createEvento",savedEvento.getId_evento());
            return savedEvento;
        } catch (Exception e) {
            logger.error("Error creando evento - metodo createEvento",e);
            throw new DatabaseTransactionException("Error creando evento",e);
        }
    }

    @Override
    public Evento updateEvento(Long id, EventoUpdateRequest updateRequest){
        logger.info("Updating evento with ID: {} and request: {} - method updateEvento", id, updateRequest);
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evento not found"));
            
        if (updateRequest.getNom_evento() != null) {
            logger.info("Updating nom_evento to: {} - method updateEvento", updateRequest.getNom_evento());
            evento.setNom_evento(updateRequest.getNom_evento());
        }
        if (updateRequest.getDescripcion() != null) {
            logger.info("Updating descripcion to: {} - method updateEvento", updateRequest.getDescripcion());
            evento.setDescripcion(updateRequest.getDescripcion());
        }
        if (updateRequest.getFecha_evento() != null) {
            logger.info("Updating fecha_evento to: {} - method updateEvento", updateRequest.getFecha_evento());
            evento.setFecha_evento(updateRequest.getFecha_evento());
        }
        if (updateRequest.getDireccion_evento() != null) {
            logger.info("Updating direccion_evento to: {} - method updateEvento", updateRequest.getDireccion_evento());
            evento.setDireccion_evento(updateRequest.getDireccion_evento());
        }
        if (updateRequest.getCoordinador_evento() != null) {
            logger.info("Updating oordinador_evento to: {} - method updateEvento", updateRequest.getCoordinador_evento());
            evento.setCoordinador_evento(updateRequest.getCoordinador_evento());
        }
        
        logger.info("Saving evento - method updateEvento");
        Evento updatedEvento = eventoRepository.save(evento);
        logger.info("Evento updated successfully. Evento ID: {}", updatedEvento.getId_evento());
        return updatedEvento;
    }

    @Override
    public void deleteEventoById(Long id){
        logger.info("Deleting evento by ID: {} - method deleteEventoById", id);
        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isEmpty()) {
            logger.info("Evento not found - method deleteEventoById");
            throw new ResourceNotFoundException("evento not found");
        }
        logger.info("Deleting Evento - method deleteEventoById");
        eventoRepository.deleteById(id);
        logger.info("Evento deleted successfully. Evento ID: {} - method deleteEventoById", id);
    }
}