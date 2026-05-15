package com.tankOfTitans.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String imagen;

    @Column(nullable = false)
    private int precio = 0;

    @Column(name = "es_comprable", nullable = false)
    private boolean esComprable = true;

    public Avatar() {
    }

    public Avatar(String nombre, String imagen, int precio, boolean esComprable) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.esComprable = esComprable;
    }

    public boolean isEsComprable() {
        return esComprable;
    }

    public void setEsComprable(boolean esComprable) {
        this.esComprable = esComprable;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
