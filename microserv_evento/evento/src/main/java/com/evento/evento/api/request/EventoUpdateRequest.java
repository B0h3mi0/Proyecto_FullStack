package com.evento.evento.api.request;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventoUpdateRequest {

    @NotNull(message = "Campo no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9._\\- ]+$", message = "El nombre del evento solo puede contener letras, números, puntos, guiones bajos y guiones medios")
    @Column(name = "nom_evento")
    private String nom_evento;

    @NotNull(message = "Campo no puede ser nulo")
    @Column(name = "descripcion")
    @Size(min = 30, max = 150 , message = "La descripcion debe tener un minimo de 30 caracteres y un maximo de 150")
    private String descripcion;

    @NotNull(message = "Campo no puede ser nulo")
    @Column(name = "fecha_evento")
    private LocalDate fecha_evento;

    @NotNull(message = "Campo no puede ser nulo")
    @Column(name = "direccion_evento")
    private String direccion_evento;

    @NotNull(message = "Campo no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9._\\- ]+$", message = "El nombre del coordinador solo puede contener letras, números, puntos, guiones bajos y guiones medios")
    @Column(name = "coordinador_evento")
    private String coordinador_evento;
}
