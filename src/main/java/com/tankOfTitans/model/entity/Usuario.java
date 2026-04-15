package com.tankOfTitans.model.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
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

}
