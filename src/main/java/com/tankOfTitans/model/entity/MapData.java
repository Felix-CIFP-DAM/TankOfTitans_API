package com.tankOfTitans.model.entity;

public class MapData {
    private Tile[][] suelo;
    private Tile[][] objetos    ;

    public MapData() {
    }

    public MapData(Tile[][] suelo, Tile[][] objetos) {
        this.suelo = suelo;
        this.objetos = objetos;
    }

    public Tile[][] getSuelo() {
        return suelo;
    }

    public void setSuelo(Tile[][] suelo) {
        this.suelo = suelo;
    }

    public Tile[][] getObjetos() {
        return objetos;
    }

    public void setObjetos(Tile[][] objetos) {
        this.objetos = objetos;
    }
}
