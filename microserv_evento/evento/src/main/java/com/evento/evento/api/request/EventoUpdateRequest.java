package com.evento.evento.api.request;
import java.time.LocalDate;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventoUpdateRequest {

    @NotNull
    @Size (min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$")
    private String nom_evento;

    @NotNull
    @Size(max = 150 )
    private String descripcion;

    @NotNull
    @Size(max = 150 )
    private String direccion_evento;

    @NotNull
    @Size(max = 100 )
    private String coordinador_evento;

    private LocalDate fecha_evento;
}
