package com.evento.evento.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evento.evento.api.request.EventoUpdateRequest;
import com.evento.evento.model.Evento;
import com.evento.evento.service.EventoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {
    // METODOS ////////////////////
    private static final Logger logger = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    private final EventoService eventoService;

    @GetMapping
    public CollectionModel<EntityModel<Evento>> getAllEventos() {
        List<Evento> eventos = eventoService.getAllEventos();
        logger.info("GET /eventos");
        logger.info("Retornando todos los estudiantes");
        List<EntityModel<Evento>> eventosResources = eventos.stream()
            .map( evento -> EntityModel.of(evento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(evento.getId_evento())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos());
        CollectionModel<EntityModel<Evento>> resources = CollectionModel.of(eventosResources, linkTo.withRel("eventos"));

        return resources;
    }

    /* @GetMapping("/all")
    public ResponseEntity<List<Evento>> getAllEventos(){
        logger.info("Getting all eventos");
        List<Evento> eventos = eventoService.getAllEventos();
        logger.info("Found {} eventos", eventos.size());
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    } */
    
    @GetMapping("/{id}")
    public EntityModel<Evento> getEventoById(@PathVariable Long id) {
        Optional<Evento> evento = eventoService.getEventoById(id);

        if (evento.isPresent()) {
            return EntityModel.of(evento.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
        } else {
            throw new EventoNotFoundException("Evento not found with id: " + id);
        }
    }
    /* @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        logger.info("Getting a evento by ID: {}", id);
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(value -> {
            logger.info("Evento found by ID: {}", id);
            return new ResponseEntity<>(value, HttpStatus.OK);
        })
        .orElseGet(() -> {
            logger.info("Evento not found by ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    } */

    @PostMapping
    public EntityModel<Evento> createEvento(@Validated @RequestBody Evento evento) {
        Evento createdEvento = eventoService.createEvento(evento);
            return EntityModel.of(createdEvento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(createdEvento.getId_evento())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));

    }

    /* @PostMapping()
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        logger.info("Creating a new evento with request: {}", evento);
        Evento savedEvento = eventoService.createEvento(evento);
        logger.info("Evento created successfully. evento ID: {}", savedEvento.getId_evento());
        return new ResponseEntity<>(savedEvento, HttpStatus.CREATED);
    } */
    

    @PutMapping("/{id}")
    public EntityModel<Evento> updateEvento(@PathVariable Long id, @RequestBody EventoUpdateRequest updateRequest) {
        Evento updatedEvento = eventoService.updateEvento(id, updateRequest);
        return EntityModel.of(updatedEvento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));

    }

    /* @PatchMapping("/{id}")
    public ResponseEntity<Evento> patchEvento(@PathVariable Long id, @RequestBody EventoUpdateRequest updateRequest) {
        logger.info("Updating a evento with ID: {} and request: {}", id, updateRequest);
        Evento updatedEvento = eventoService.updateEvento(id, updateRequest);
        logger.info("Evento updated successfully. User ID: {}", updatedEvento.getId_evento());
        return new ResponseEntity<>(updatedEvento, HttpStatus.OK);
    }
 */
    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id){
        eventoService.deleteEventoById(id);
    } 

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }
    /* @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id){
    logger.info("Deleting a evento with ID: {}", id);
    eventoService.deleteEventoById(id);
    logger.info("Evento deleted successfully. Evento ID: {}", id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } */
    
}
