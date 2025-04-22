package com.veterinaria.veterinaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity //Definicion de TABLA FACTURA
@Table(name = "factura")
public class Factura { // clase FacturaVet 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura" )
    private long id_factura;

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

/* // Constructor clase FacturaVet
    public Factura(long id_factura, String servicio, int costo_asociado, String nom_veterinario, String nom_mascota, String tipo_mascota, boolean chip, String nom_dueño){
        this.id_factura = id_factura;
        this.servicio = servicio;
        this.costo_asociado = costo_asociado;
        this.nom_veterinario = nom_veterinario;
        this.nom_mascota = nom_mascota;
        this.tipo_mascota = tipo_mascota;
        this.chip = chip;
        this.nom_dueño = nom_dueño;
    } */
// GETTERS clase FacturaVet
    /* public long getId_factura() {
        return id_factura;
    }

    public String getServicio() {
        return servicio;
    }

    public int getCosto_asociado() {
        return costo_asociado;
    }

    public String getNom_veterinario() {
        return nom_veterinario;
    }

    public String getNom_mascota() {
        return nom_mascota;
    }

    public String getTipo_mascota() {
        return tipo_mascota;
    }

    public boolean getChip() {
        return chip;
    }

    public String getNom_dueño() {
        return nom_dueño;
    }
    public void setId_factura(long id_factura) {
        this.id_factura = id_factura;
    }
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    public void setCosto_asociado(int costo_asociado) {
        this.costo_asociado = costo_asociado;
    }
    public void setNom_veterinario(String nom_veterinario) {
        this.nom_veterinario = nom_veterinario;
    }
    public void setNom_mascota(String nom_mascota) {
        this.nom_mascota = nom_mascota;
    }
    public void setTipo_mascota(String tipo_mascota) {
        this.tipo_mascota = tipo_mascota;
    }
    public void setChip(boolean chip) {
        this.chip = chip;
    }
    public void setNom_dueño(String nom_dueño) {
        this.nom_dueño = nom_dueño;
    } */
}
