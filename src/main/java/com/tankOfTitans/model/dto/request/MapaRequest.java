package com.tankOfTitans.model.dto.request;

import java.util.List;
import com.tankOfTitans.model.dto.CasillaDTO;

import com.tankOfTitans.model.entity.MapData;


public class MapaRequest {
	private String nombre;
    private List<CasillaDTO> casillas;
	private MapData data;
    
	public MapaRequest() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CasillaDTO> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<CasillaDTO> casillas) {
		this.casillas = casillas;
	}

	public MapData getData() {
		return data;
	}

	public void setData(MapData data) {
		this.data = data;
	}
}
