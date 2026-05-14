package com.tankOfTitans.model.dto;

import java.util.List;

public class JugadorLobbyDTO {
    private Long id;
    private String nickname;
    private int icono;
    private String iconoImagen;
    private boolean listo;
    private List<Long> tanquesIds;
    private int pa;
    private int vida;

    public JugadorLobbyDTO() {}

    public JugadorLobbyDTO(Long id, String nickname, int icono, String iconoImagen, boolean listo, List<Long> tanquesIds, int pa, int vida) {
        this.id = id;
        this.nickname = nickname;
        this.icono = icono;
        this.iconoImagen = iconoImagen;
        this.listo = listo;
        this.tanquesIds = tanquesIds;
        this.pa = pa;
        this.vida = vida;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public int getIcono() { return icono; }
    public void setIcono(int icono) { this.icono = icono; }

    public String getIconoImagen() { return iconoImagen; }
    public void setIconoImagen(String iconoImagen) { this.iconoImagen = iconoImagen; }

    public boolean isListo() { return listo; }
    public void setListo(boolean listo) { this.listo = listo; }

    public List<Long> getTanquesIds() { return tanquesIds; }
    public void setTanquesIds(List<Long> tanquesIds) { this.tanquesIds = tanquesIds; }

    public int getPa() { return pa; }
    public void setPa(int pa) { this.pa = pa; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
}
