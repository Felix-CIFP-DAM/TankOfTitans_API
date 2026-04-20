package com.tankOfTitans.model.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	@Column(name = "created_at")
	   private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
	
	

	public Usuario() {
		
	}

	public Usuario(@NotBlank String nombre, @NotBlank String nickname, @Email @NotBlank String email,
			@NotBlank String password) {
		
		this.nombre = nombre;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
