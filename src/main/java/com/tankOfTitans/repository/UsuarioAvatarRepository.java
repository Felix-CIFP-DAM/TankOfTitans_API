package com.tankOfTitans.repository;

import com.tankOfTitans.model.entity.UsuarioAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioAvatarRepository extends JpaRepository<UsuarioAvatar, Long> {
    List<UsuarioAvatar> findByUsuarioId(Long usuarioId);
    
    @Query("SELECT ua.avatar.id FROM UsuarioAvatar ua WHERE ua.usuario.id = :usuarioId")
    List<Long> findAvatarIdsByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    boolean existsByUsuarioIdAndAvatarId(Long usuarioId, Long avatarId);
}
