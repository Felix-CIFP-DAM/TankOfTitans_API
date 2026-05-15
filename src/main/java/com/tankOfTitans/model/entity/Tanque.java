package com.tankOfTitans.model.entity;

import com.tankOfTitans.model.entity.enums.TipoTanque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tanques")
public class Tanque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTanque tipo;

    @Column(nullable = false)
    private int hp;

    @Column(nullable = false)
    private int ataque;

    @Column(nullable = false)
    private int defensa;

    @Column(name = "rango_movimiento", nullable = false)
    private int rangoMovimiento;

    @Column(name = "rango_ataque", nullable = false)
    private int rangoAtaque;

    @Column(nullable = false)
    private int precio;

    @Column(name = "imagen_portada", nullable = false)
    private String imagenPortada;

    @Column(nullable = false)
    private String miniatura;

    @Column(name = "coste_poner", nullable = false)
    private int costePoner = 10;

    @Column(name = "coste_atacar", nullable = false)
    private int costeAtacar = 15;

    @Column(name = "coste_mover", nullable = false)
    private int costeMover = 5;

    @Column(name = "es_comprable", nullable = false)
    private boolean esComprable = true;

    public Tanque() {}

    public Tanque(String nombre, TipoTanque tipo, int hp, int ataque, int defensa, int rangoMovimiento, int rangoAtaque, int precio, String imagenPortada, String miniatura, int costePoner, int costeAtacar, int costeMover, boolean esComprable) {
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
