package com.tankOfTitans.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
	@NotBlank(message = "El nickname de usuario es obligatorio")
	private String nickname;
	
	 @Email(message = "El email no tiene formato válido")
	 @NotBlank(message = "El email es obligatorio")
	 private String email;
	 
	 @NotBlank(message ="La contraseña es obligaroria")
	 private String password;
	
}
