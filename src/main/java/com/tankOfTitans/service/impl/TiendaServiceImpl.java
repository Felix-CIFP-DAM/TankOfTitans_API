package com.tankOfTitans.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tankOfTitans.model.dto.response.AvatarResponse;
import com.tankOfTitans.model.dto.response.TanqueResponse;
import com.tankOfTitans.model.dto.response.TiendaResponse;
import com.tankOfTitans.model.entity.Avatar;
import com.tankOfTitans.model.entity.Tanque;
import com.tankOfTitans.model.entity.Usuario;
import com.tankOfTitans.model.entity.UsuarioAvatar;
import com.tankOfTitans.model.entity.UsuarioTanque;
import com.tankOfTitans.repository.AvatarRepository;
import com.tankOfTitans.repository.TanqueRepository;
import com.tankOfTitans.repository.UsuarioAvatarRepository;
import com.tankOfTitans.repository.UsuarioRepository;
import com.tankOfTitans.repository.UsuarioTanqueRepository;
import com.tankOfTitans.service.TiendaService;

@Service
public class TiendaServiceImpl implements TiendaService {

    private final UsuarioRepository usuarioRepository;
    private final TanqueRepository tanqueRepository;
    private final AvatarRepository avatarRepository;
    private final UsuarioTanqueRepository usuarioTanqueRepository;
    private final UsuarioAvatarRepository usuarioAvatarRepository;

    public TiendaServiceImpl(UsuarioRepository usuarioRepository, TanqueRepository tanqueRepository,
                             AvatarRepository avatarRepository, UsuarioTanqueRepository usuarioTanqueRepository,
                             UsuarioAvatarRepository usuarioAvatarRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tanqueRepository = tanqueRepository;
        this.avatarRepository = avatarRepository;
        this.usuarioTanqueRepository = usuarioTanqueRepository;
        this.usuarioAvatarRepository = usuarioAvatarRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public TiendaResponse obtenerContenidoTienda(Long usuarioId) {
        System.out.println("[JAVA][TiendaService] 🛒 Cargando tienda para usuarioId: " + usuarioId);
        
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        try {
            // Optimización: traer solo los IDs para evitar N+1 selects en DB remota
            List<Long> tanquesComprados = usuarioTanqueRepository.findTanqueIdsByUsuarioId(usuarioId);
            List<Long> avataresComprados = usuarioAvatarRepository.findAvatarIdsByUsuarioId(usuarioId);

            List<TanqueResponse> tanques = tanqueRepository.findAll().stream()
                    .filter(Tanque::isEsComprable)
                    .map(t -> new TanqueResponse(
                            t.getId(), t.getNombre(), t.getTipo(), t.getHp(), t.getAtaque(), t.getDefensa(),
                            t.getRangoMovimiento(), t.getRangoAtaque(), t.getPrecio(), t.getImagenPortada(),
                            t.getMiniatura(), t.getCostePoner(), t.getCosteAtacar(), t.getCosteMover(),
                            t.isEsComprable(), tanquesComprados.contains(t.getId())
                    )).collect(Collectors.toList());

            List<AvatarResponse> avatares = avatarRepository.findAll().stream()
                    .filter(Avatar::isEsComprable)
                    .map(a -> new AvatarResponse(
                            a.getId(), a.getNombre(), a.getImagen(), a.getPrecio(),
                            avataresComprados.contains(a.getId()), a.isEsComprable()
                    )).collect(Collectors.toList());

            System.out.println("[JAVA][TiendaService] ✅ Tienda cargada: " + tanques.size() + " tanques, " + avatares.size() + " avatares");
            return new TiendaResponse(usuario.getMonedas(), tanques, avatares);
            
        } catch (Exception e) {
            System.err.println("[JAVA][TiendaService] ❌ ERROR mapeando tienda: " + e.getMessage());
            throw new RuntimeException("Error al procesar el catálogo: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void comprarTanque(Long usuarioId, Long tanqueId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Tanque tanque = tanqueRepository.findById(tanqueId)
                .orElseThrow(() -> new RuntimeException("Tanque no encontrado"));

        if (!tanque.isEsComprable()) {
            throw new RuntimeException("Este tanque no está a la venta");
        }

        if (usuarioTanqueRepository.existsByUsuarioIdAndTanqueId(usuarioId, tanqueId)) {
            throw new RuntimeException("Ya posees este tanque");
        }

        if (usuario.getMonedas() < tanque.getPrecio()) {
            throw new RuntimeException("Monedas insuficientes");
        }

        usuario.setMonedas(usuario.getMonedas() - tanque.getPrecio());
        usuarioRepository.save(usuario);

        UsuarioTanque ut = new UsuarioTanque(usuario, tanque);
        usuarioTanqueRepository.save(ut);
    }

    @Override
    @Transactional
    public void comprarAvatar(Long usuarioId, Long avatarId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Avatar avatar = avatarRepository.findById(avatarId)
                .orElseThrow(() -> new RuntimeException("Avatar no encontrado"));

        if (!avatar.isEsComprable()) {
            throw new RuntimeException("Este avatar no está a la venta");
        }

        if (usuarioAvatarRepository.existsByUsuarioIdAndAvatarId(usuarioId, avatarId)) {
            throw new RuntimeException("Ya posees este avatar");
        }

        if (usuario.getMonedas() < avatar.getPrecio()) {
            throw new RuntimeException("Monedas insuficientes");
        }

        usuario.setMonedas(usuario.getMonedas() - avatar.getPrecio());
        usuarioRepository.save(usuario);

        UsuarioAvatar ua = new UsuarioAvatar(usuario, avatar);
        usuarioAvatarRepository.save(ua);
    }
}
