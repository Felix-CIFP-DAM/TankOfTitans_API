package com.tankOfTitans.model.dto.response;

import java.util.List;

public class TiendaResponse {
    private long monedas;
    private List<TanqueResponse> tanques;
    private List<AvatarResponse> avatares;

    public TiendaResponse() {}

    public TiendaResponse(long monedas, List<TanqueResponse> tanques, List<AvatarResponse> avatares) {
        this.monedas = monedas;
        this.tanques = tanques;
        this.avatares = avatares;
    }

    public long getMonedas() {
        return monedas;
    }

    public void setMonedas(long monedas) {
        this.monedas = monedas;
    }

    public List<TanqueResponse> getTanques() {
        return tanques;
    }

    public void setTanques(List<TanqueResponse> tanques) {
        this.tanques = tanques;
    }

    public List<AvatarResponse> getAvatares() {
        return avatares;
    }

    public void setAvatares(List<AvatarResponse> avatares) {
        this.avatares = avatares;
    }
}
