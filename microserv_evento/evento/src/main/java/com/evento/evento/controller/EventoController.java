package com.evento.evento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evento.evento.model.Evento;
import com.evento.evento.service.EventoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {
    // METODOS GetMapping ////////////////////
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getAllEventos(){
        return eventoService.getAllEventos();
    }
        
    @GetMapping("/{id}")
    public Optional<Evento> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id);
    }

    @PostMapping
    public Evento creaEvento(@RequestBody Evento evento) {
        return eventoService.createEvento(evento);
    }
    
    @PutMapping("/{id}")
    public Evento updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.updateEvento(id, evento);
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id){
        eventoService.deleteEvento(id);
    }
    
}
