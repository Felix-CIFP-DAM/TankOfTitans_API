package com.tankOfTitans.model.dto;

public class CasillaDTO {
	private int posX;
    private int posY;
    private boolean transitable;

    public CasillaDTO() {
    	
    }

    public CasillaDTO(int posX, int posY, boolean transitable) {
        this.posX = posX;
        this.posY = posY;
        this.transitable = transitable;
    }

    public int getPosX() {
    	return posX; 
    }
    
    public int getPosY() {
    	return posY;
    }
    
    public boolean isTransitable() {
    	return transitable; 
    }

    public void setPosX(int posX) {
    	this.posX = posX; 
    }
    public void setPosY(int posY) { 
    	this.posY = posY; 
    }
    public void setTransitable(boolean transitable) {
    	this.transitable = transitable;
    }

}
