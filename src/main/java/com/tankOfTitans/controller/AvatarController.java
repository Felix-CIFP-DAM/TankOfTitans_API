package com.tankOfTitans.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        return ResponseEntity.ok(avatarService.crearAvatar(avatar.getNombre(), avatar.getImagen()));
    }

    @GetMapping
    public ResponseEntity<List<Avatar>> listarAvatares() {
        return ResponseEntity.ok(avatarService.listarAvatares());
    }
}
