package com.tankOfTitans.model.dto.request;

import com.tankOfTitans.model.entity.MapData;


public class MapaRequest {
	private String nombre;
	private MapData data;
    
	public MapaRequest() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public MapData getData() {
		return data;
	}

	public void setData(MapData data) {
		this.data = data;
	}
}
