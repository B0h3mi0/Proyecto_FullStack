package com.veterinaria.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.veterinaria.veterinaria.model.Factura;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class FacturaRepositoryTest {
    @Autowired
    private FacturaRepository facturaRepository;


    @Test
    public void guardarFacturaTest(){
        Factura factura = new Factura();
        factura.setServicio("Cirugia");
        factura.setCosto_asociado(180000);
        factura.setNom_veterinario("Milo Fernandez");
        factura.setNom_mascota("Alex");
        factura.setTipo_mascota("gato-felino");
        factura.setChip(true);
        factura.setNom_dueño("Carlos_Fuenzalida");
        Factura resultado = facturaRepository.save(factura);
        assertNotNull(resultado.getId_factura());
        assertEquals( "Cirugia", resultado.getServicio());
        assertEquals( 180000, resultado.getCosto_asociado());
        assertEquals( "Milo Fernandez", resultado.getNom_veterinario());
        assertEquals( "Alex", resultado.getNom_mascota());
        assertEquals( "gato-felino", resultado.getTipo_mascota());
        assertEquals( "Carlos_Fuenzalida", resultado.getNom_dueño());
    }
}
    