package com.tankOfTitans.repository;

import com.tankOfTitans.model.entity.PartidaBase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartidaBaseRepository extends JpaRepository<PartidaBase, Long> {
    List<PartidaBase> findByPartidaId(Long partidaId);
    boolean existsByPartidaIdAndEsHost(Long partidaId, boolean esHost);
}
