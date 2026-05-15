package com.tankOfTitans.model.dto.response;

public class AvatarResponse {
    private Long id;
    private String nombre;
    private String imagen;
    private int precio;
    private boolean comprado;
    private boolean esComprable;

    public AvatarResponse() {}

    public AvatarResponse(Long id, String nombre, String imagen, int precio, boolean comprado, boolean esComprable) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.comprado = comprado;
        this.esComprable = esComprable;
    }

    public boolean isEsComprable() {
        return esComprable;
    }

    public void setEsComprable(boolean esComprable) {
        this.esComprable = esComprable;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }
}
