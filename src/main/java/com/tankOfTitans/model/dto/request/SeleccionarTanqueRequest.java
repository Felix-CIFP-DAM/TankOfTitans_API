package com.tankOfTitans.model.dto.request;

public class SeleccionarTanqueRequest {
    private Long usuarioId;
    private Long tanqueId;

    public SeleccionarTanqueRequest() {}

    public SeleccionarTanqueRequest(Long usuarioId, Long tanqueId) {
        this.usuarioId = usuarioId;
        this.tanqueId = tanqueId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getTanqueId() {
        return tanqueId;
    }

    public void setTanqueId(Long tanqueId) {
        this.tanqueId = tanqueId;
    }
}
