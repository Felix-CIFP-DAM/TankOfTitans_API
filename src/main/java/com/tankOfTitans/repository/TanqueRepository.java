package com.tankOfTitans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tankOfTitans.model.entity.Tanque;

public interface TanqueRepository extends JpaRepository<Tanque, Long> {
	}
