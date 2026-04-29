package com.tankOfTitans.model.entity;


import java.time.LocalDateTime;

import com.tankOfTitans.model.entity.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
	@NotBlank
    @Column(name = "nombre", unique = false, nullable = false, length = 50)
    private String nombre;
	
	@NotBlank
    @Column(name = "nickname", unique = true, nullable = false, length = 50)
    private String nickname;
	
	@NotBlank
	@Column(nullable = false)
    private String password;
	
	@Email
    @NotBlank
    @Column(unique = true, nullable = false, length = 100)
    private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Rol rol = Rol.USER;
	
	@Column(nullable = false)
	private int partidasJugadas = 0;

	@Column(nullable = false)
	private int victorias = 0;

	@Column(nullable = false)
	private int derrotas = 0;

	@Column(nullable = false)
	private int empates = 0;
	
	@Column(name = "created_at")
	   private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
	
	

	public Usuario() {
		
	}

	
	public Usuario(@NotBlank String nombre, @NotBlank String nickname, @NotBlank String password,
			@Email @NotBlank String email) {
		this.nombre = nombre;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.rol = rol.USER;
	}
	
	public Usuario(String nombre, String nickname, String password, String email, Rol rol) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.rol = rol;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}
	
	
	
	

}
