package com.tankOfTitans.model.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequest {
	
	@NotBlank(message = "El nickname es obligatorio")
    private String nickname;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

}
