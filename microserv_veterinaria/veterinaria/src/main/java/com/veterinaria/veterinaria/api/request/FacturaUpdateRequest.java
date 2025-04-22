package com.veterinaria.veterinaria.api.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FacturaUpdateRequest {
    
    @NotNull(message = "Campo no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9._\\- ]+$", message = "El nombre del servicio solo puede contener letras, números, puntos, guiones bajos y guiones medios")
    @Size(min = 5, max =50, message = "El campo servicio debe tener un minimo de 5 caracteres y un maximo de 50")
    @Column(name = "servicio" )
    private String servicio; 

    @NotNull(message = "Campo no puede ser nulo")
    @PositiveOrZero(message = "El número debe ser positivo o cero")
    @Column(name = "costo_asociado" )
    private int costo_asociado; 

    @NotNull(message = "Campo no puede ser nulo")
    @Column(name = "nom_veterinario" )
    private String nom_veterinario;

    @NotNull(message = "Campo no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9._\\- ]+$", message = "El nombre de la mascota solo puede contener letras, números, puntos, guiones bajos y guiones medios")
    @Column(name = "nom_mascota" )
    private String nom_mascota;

    @NotNull(message = "Campo no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9._\\- ]+$", message = "El tipo mascota solo puede contener letras, números, puntos, guiones bajos y guiones medios")
    @Column(name = "tipo_mascota" )
    private String tipo_mascota;

    @NotNull(message = "Campo no puede ser nulo")
    @Column(name = "chip" )
    private boolean chip;

    @NotNull(message = "Campo no puede ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "El nombre del dueño solo puede contener letras, números, puntos, guiones bajos y guiones medios")
    @Column(name = "nom_dueño" )
    private String nom_dueño;
}
