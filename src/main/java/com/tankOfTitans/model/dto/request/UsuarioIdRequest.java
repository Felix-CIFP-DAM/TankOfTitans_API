package com.tankOfTitans.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioIdRequest {
    @JsonProperty("usuarioId")
    private Long usuarioId;

    public UsuarioIdRequest() {}

    public UsuarioIdRequest(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
