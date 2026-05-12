package com.tankOfTitans.repository;

import com.tankOfTitans.model.entity.Tanque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TanqueRepository extends JpaRepository<Tanque, Long> {
}
