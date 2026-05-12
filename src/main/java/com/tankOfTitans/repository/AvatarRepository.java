package com.tankOfTitans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tankOfTitans.model.entity.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
