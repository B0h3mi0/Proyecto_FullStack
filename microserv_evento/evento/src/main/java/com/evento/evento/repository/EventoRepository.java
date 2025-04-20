package com.evento.evento.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evento.evento.model.Evento;
public interface EventoRepository extends JpaRepository <Evento, Long>{
    
}
