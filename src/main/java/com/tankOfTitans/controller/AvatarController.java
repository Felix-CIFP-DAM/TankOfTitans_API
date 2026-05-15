package com.tankOfTitans.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tankOfTitans.model.dto.response.AvatarResponse;
import com.tankOfTitans.model.entity.Avatar;
import com.tankOfTitans.service.AvatarService;

@RestController
@RequestMapping("/api/avatars")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public ResponseEntity<Avatar> crearAvatar(@RequestBody Avatar avatar) {
        return ResponseEntity.ok(avatarService.crearAvatar(avatar.getNombre(), avatar.getImagen(), avatar.getPrecio(), avatar.isEsComprable()));
    }

    @GetMapping
    public ResponseEntity<List<Avatar>> listarAvatares() {
        return ResponseEntity.ok(avatarService.listarAvatares());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AvatarResponse>> listarAvataresParaUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(avatarService.listarAvataresParaUsuario(usuarioId));
    }
}
