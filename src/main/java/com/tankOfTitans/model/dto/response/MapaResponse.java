package com.tankOfTitans.model.dto.response;

import java.util.List;
import com.tankOfTitans.model.dto.CasillaDTO;

public class MapaResponse {
	private Long id;
    private String nombre;
    private int ancho;
    private int alto;
    private List<CasillaDTO> casillas;
    
	public MapaResponse() {

	}

	public MapaResponse(Long id, String nombre, int ancho, int alto, List<CasillaDTO> casillas) {
		this.id = id;
		this.nombre = nombre;
		this.ancho = ancho;
		this.alto = alto;
		this.casillas = casillas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public List<CasillaDTO> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<CasillaDTO> casillas) {
		this.casillas = casillas;
	}
	
	
	
	
    
    
}
