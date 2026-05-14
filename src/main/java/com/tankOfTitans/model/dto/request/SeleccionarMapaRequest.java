package com.tankOfTitans.model.dto.request;

public class SeleccionarMapaRequest {
    private Long usuarioId;
    private Long mapaId;

    public SeleccionarMapaRequest() {}

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getMapaId() { return mapaId; }
    public void setMapaId(Long mapaId) { this.mapaId = mapaId; }
}
