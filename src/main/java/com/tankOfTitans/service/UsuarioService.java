package com.tankOfTitans.service;

import java.util.List;

import com.tankOfTitans.model.entity.Usuario; 

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    void deleteById(Long id);
}