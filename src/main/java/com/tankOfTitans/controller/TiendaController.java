package com.tankOfTitans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tankOfTitans.model.dto.response.TiendaResponse;
import com.tankOfTitans.service.TiendaService;

@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

    private final TiendaService tiendaService;

    public TiendaController(TiendaService tiendaService) {
        this.tiendaService = tiendaService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<TiendaResponse> obtenerTienda(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(tiendaService.obtenerContenidoTienda(usuarioId));
    }

    @PostMapping("/{usuarioId}/comprar/tanque/{tanqueId}")
    public ResponseEntity<?> comprarTanque(@PathVariable Long usuarioId, @PathVariable Long tanqueId) {
        try {
            tiendaService.comprarTanque(usuarioId, tanqueId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{usuarioId}/comprar/avatar/{avatarId}")
    public ResponseEntity<?> comprarAvatar(@PathVariable Long usuarioId, @PathVariable Long avatarId) {
        try {
            tiendaService.comprarAvatar(usuarioId, avatarId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
