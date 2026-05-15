package com.tankOfTitans.repository;

import com.tankOfTitans.model.entity.UsuarioAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioAvatarRepository extends JpaRepository<UsuarioAvatar, Long> {
    List<UsuarioAvatar> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndAvatarId(Long usuarioId, Long avatarId);
}
