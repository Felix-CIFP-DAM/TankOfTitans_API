package com.tankOfTitans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tankOfTitans.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
