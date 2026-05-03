package com.tankOfTitans.service;

import java.util.List;

import com.tankOfTitans.document.EstadisticasJugador;
import com.tankOfTitans.document.HistorialPartida;

public interface EstadisticasService {
	void sincronizarEstadisticas(Long usuarioId);
    void guardarHistorialPartida(Long partidaId);
    List<EstadisticasJugador> getRankingJugadores();
    List<HistorialPartida> getHistorialPartidas();
}
