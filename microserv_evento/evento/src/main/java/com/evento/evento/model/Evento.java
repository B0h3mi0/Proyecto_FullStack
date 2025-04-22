package com.evento.evento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //Definicion de TABLA EVENTO
@Table(name = "evento")
public class Evento { //clase Evento

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private long id_evento;

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
    
   /*  //constructor clase Evento
    public Evento(long id_evento, String nom_evento, String descripcion, LocalDate fecha_evento, String direccion_evento,
            String coordinador_evento) {
        this.id_evento = id_evento;
        this.nom_evento = nom_evento;
        this.descripcion = descripcion;
        this.fecha_evento = fecha_evento;
        this.direccion_evento = direccion_evento;
        this.coordinador_evento = coordinador_evento;
    }
 */
// GETTERS clase evento

   /*  public long getId_evento() {
        return id_evento;
    }

    public String getNom_evento() {
        return nom_evento;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha_evento() {
        return fecha_evento;
    }

    public String getDireccion_evento() {
        return direccion_evento;
    }

    public String getCoordinador_evento() {
        return coordinador_evento;
    }

    public void setId_evento(long id_evento) {
        this.id_evento = id_evento;
    }

    public void setNom_evento(String nom_evento) {
        this.nom_evento = nom_evento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha_evento(LocalDate fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public void setDireccion_evento(String direccion_evento) {
        this.direccion_evento = direccion_evento;
    }

    public void setCoordinador_evento(String coordinador_evento) {
        this.coordinador_evento = coordinador_evento;
    }
 */


}
