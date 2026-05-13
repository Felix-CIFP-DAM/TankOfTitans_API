package com.tankOfTitans.model.entity;

public class Tile {
    private String sheet;
    private String tipo;
    private Integer x;
    private Integer y;

    public Tile() {
    }

    public Tile(String sheet, String tipo, Integer x, Integer y) {
        this.sheet = sheet;
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}