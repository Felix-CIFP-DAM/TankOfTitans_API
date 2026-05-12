package com.tankOfTitans.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios_tanques")
public class UsuarioTanque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tanque_id", nullable = false)
    private Tanque tanque;

    @Column(name = "adquirido_en")
    private LocalDateTime adquiridoEn;

    public UsuarioTanque() {}

    public UsuarioTanque(Usuario usuario, Tanque tanque) {
        this.usuario = usuario;
        this.tanque = tanque;
        this.adquiridoEn = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Tanque getTanque() { return tanque; }
    public void setTanque(Tanque tanque) { this.tanque = tanque; }

    public LocalDateTime getAdquiridoEn() { return adquiridoEn; }
    public void setAdquiridoEn(LocalDateTime adquiridoEn) { this.adquiridoEn = adquiridoEn; }
}
