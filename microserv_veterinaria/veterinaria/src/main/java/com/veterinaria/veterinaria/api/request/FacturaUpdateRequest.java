package com.veterinaria.veterinaria.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FacturaUpdateRequest {
    
    @NotNull
    @Size(min = 3, max =50)
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$")
    private String servicio; 

    @NotNull
    private int costo_asociado; 

    @NotNull
    private String nom_veterinario;

    @NotNull
    private String nom_mascota;

    @NotNull
    private String tipo_mascota;

    private boolean chip;

    @NotNull
    private String nom_dueño;
}
