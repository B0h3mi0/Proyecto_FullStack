package com.evento.evento.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.evento.evento.model.Evento;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class EventoRepositoryTest {
    @Autowired
    private EventoRepository eventoRepository;


    @Test
    public void guardarEventoTest(){
        Evento evento = new Evento();
        evento.setNom_evento("Adopta un koala");
        evento.setDescripcion("Evento a beneficio para la fundacion rescata un koala, tendremos distintos stands y concursos para que puedas disfrutas con tu familia, habran stands donde podras realizar tu donacion a la fundacion.");
        evento.setFecha_evento(LocalDate.of(2025, 8, 26));
        evento.setDireccion_evento("Las acacias 4251,santiago");
        evento.setCoordinador_evento("Alex Ortiz");
        Evento resultado = eventoRepository.save(evento);
        assertNotNull(resultado.getId_evento());
        assertEquals( "Adopta un koala", resultado.getNom_evento());
        assertEquals( "Evento a beneficio para la fundacion rescata un koala, tendremos distintos stands y concursos para que puedas disfrutas con tu familia, habran stands donde podras realizar tu donacion a la fundacion.", resultado.getDescripcion());
        assertEquals( LocalDate.of(2025, 8, 26), resultado.getFecha_evento());
        assertEquals( "Las acacias 4251,santiago", resultado.getDireccion_evento());
        assertEquals( "Alex Ortiz", resultado.getCoordinador_evento());
    }
    
}