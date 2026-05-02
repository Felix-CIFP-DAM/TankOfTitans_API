package com.tankOfTitans.model.dto.request;

import java.util.List;
import com.tankOfTitans.model.dto.CasillaDTO;


public class MapaRequest {
	private String nombre;
    private List<CasillaDTO> casillas;
    
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
	
	
    
    
}
