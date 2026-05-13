package com.tankOfTitans.controller;

import com.tankOfTitans.model.dto.request.ActualizarPerfilRequest;
import com.tankOfTitans.model.dto.response.PerfilResponse;
import com.tankOfTitans.service.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.tankOfTitans.model.dto.response.TanqueResponse;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<PerfilResponse> obtenerPerfil(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(perfilService.obtenerPerfil(usuarioId));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PerfilResponse> actualizarPerfil(
            @RequestBody ActualizarPerfilRequest request) {
        return ResponseEntity.ok(perfilService.actualizarPerfil(request));
    }

    @GetMapping("/{usuarioId}/tanques")
    public ResponseEntity<List<TanqueResponse>> obtenerTanques(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(perfilService.obtenerTanquesUsuario(usuarioId));
    }
}
