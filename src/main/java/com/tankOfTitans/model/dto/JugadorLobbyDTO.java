package com.tankOfTitans.model.dto;

public class JugadorLobbyDTO {
    private Long id;
    private String nickname;
    private int icono;
    private String iconoImagen;
    private boolean listo;

    public JugadorLobbyDTO() {}

    public JugadorLobbyDTO(Long id, String nickname, int icono, String iconoImagen, boolean listo) {
        this.id = id;
        this.nickname = nickname;
        this.icono = icono;
        this.iconoImagen = iconoImagen;
        this.listo = listo;
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
}
