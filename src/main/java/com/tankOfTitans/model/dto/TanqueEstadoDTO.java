package com.tankOfTitans.model.dto;

import com.tankOfTitans.model.entity.enums.TipoTanque;

public class TanqueEstadoDTO {
	private int numeroTanque;
    private TipoTanque tipo;
    private int hp;
    private boolean vivo;
    private int posX;
    private int posY;

    public TanqueEstadoDTO() {}

    public TanqueEstadoDTO(int numeroTanque, TipoTanque tipo,
                           int hp, boolean vivo, int posX, int posY) {
        this.numeroTanque = numeroTanque;
        this.tipo = tipo;
        this.hp = hp;
        this.vivo = vivo;
        this.posX = posX;
        this.posY = posY;
    }

	public int getNumeroTanque() {
		return numeroTanque;
	}

	public void setNumeroTanque(int numeroTanque) {
		this.numeroTanque = numeroTanque;
	}

	public TipoTanque getTipo() {
		return tipo;
	}

	public void setTipo(TipoTanque tipo) {
		this.tipo = tipo;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
    
    
}
