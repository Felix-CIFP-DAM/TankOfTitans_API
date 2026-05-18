package com.tankOfTitans.repository;

import com.tankOfTitans.model.entity.UsuarioTanque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UsuarioTanqueRepository extends JpaRepository<UsuarioTanque, Long> {
    List<UsuarioTanque> findByUsuarioId(Long usuarioId);
    
    @Query("SELECT ut.tanque.id FROM UsuarioTanque ut WHERE ut.usuario.id = :usuarioId")
    List<Long> findTanqueIdsByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    boolean existsByUsuarioIdAndTanqueId(Long usuarioId, Long tanqueId);
}
