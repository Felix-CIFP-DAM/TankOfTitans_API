package com.tankOfTitans.model.dto.response;

import com.tankOfTitans.model.entity.enums.TipoTanque;

public class TanqueResponse {
    private Long id;
    private String nombre;
    private TipoTanque tipo;
    private int hp;
    private int ataque;
    private int defensa;
    private int rangoMovimiento;
    private int rangoAtaque;
    private int precio;
    private String imagenPortada;
    private String miniatura;
    private int costePoner;
    private int costeAtacar;
    private int costeMover;
    private boolean esComprable;

    public TanqueResponse() {}

    public TanqueResponse(Long id, String nombre, TipoTanque tipo, int hp, int ataque, int defensa, int rangoMovimiento, int rangoAtaque, int precio, String imagenPortada, String miniatura, int costePoner, int costeAtacar, int costeMover, boolean esComprable) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.rangoMovimiento = rangoMovimiento;
        this.rangoAtaque = rangoAtaque;
        this.precio = precio;
        this.imagenPortada = imagenPortada;
        this.miniatura = miniatura;
        this.costePoner = costePoner;
        this.costeAtacar = costeAtacar;
        this.costeMover = costeMover;
        this.esComprable = esComprable;
    }

    public boolean isEsComprable() {
        return esComprable;
    }

    public void setEsComprable(boolean esComprable) {
        this.esComprable = esComprable;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoTanque getTipo() { return tipo; }
    public void setTipo(TipoTanque tipo) { this.tipo = tipo; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) { this.defensa = defensa; }

    public int getRangoMovimiento() { return rangoMovimiento; }
    public void setRangoMovimiento(int rangoMovimiento) { this.rangoMovimiento = rangoMovimiento; }

    public int getRangoAtaque() { return rangoAtaque; }
    public void setRangoAtaque(int rangoAtaque) { this.rangoAtaque = rangoAtaque; }

    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }

    public String getImagenPortada() { return imagenPortada; }
    public void setImagenPortada(String imagenPortada) { this.imagenPortada = imagenPortada; }

    public String getMiniatura() { return miniatura; }
    public void setMiniatura(String miniatura) { this.miniatura = miniatura; }

    public int getCostePoner() { return costePoner; }
    public void setCostePoner(int costePoner) { this.costePoner = costePoner; }

    public int getCosteAtacar() { return costeAtacar; }
    public void setCosteAtacar(int costeAtacar) { this.costeAtacar = costeAtacar; }

    public int getCosteMover() { return costeMover; }
    public void setCosteMover(int costeMover) { this.costeMover = costeMover; }
}
