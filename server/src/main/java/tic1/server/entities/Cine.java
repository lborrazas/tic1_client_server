package tic1.server.entities;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

@Entity
@Table
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long idCine;
    @Column
    public String nombre;
    @Column
    public String location;

    public Cine() {
    }

    public long getIdCine() {
        return idCine;
    }

    public void setIdCine(long idCine) {
        this.idCine = idCine;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
