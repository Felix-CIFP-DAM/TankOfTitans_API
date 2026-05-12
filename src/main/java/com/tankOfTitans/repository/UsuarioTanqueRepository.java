package com.tankOfTitans.repository;

import com.tankOfTitans.model.entity.UsuarioTanque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioTanqueRepository extends JpaRepository<UsuarioTanque, Long> {
    List<UsuarioTanque> findByUsuarioId(Long usuarioId);
}
