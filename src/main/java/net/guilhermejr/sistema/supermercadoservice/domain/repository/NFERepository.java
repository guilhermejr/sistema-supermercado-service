package net.guilhermejr.sistema.supermercadoservice.domain.repository;

import net.guilhermejr.sistema.supermercadoservice.domain.entity.NFE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NFERepository extends JpaRepository<NFE, UUID> {

    Optional<NFE> findByUrl(String url);
    Optional<NFE> findByIdAndUsuario(UUID id, UUID usuario);
    List<NFE> findAllByUsuarioOrderByCriadoAsc(UUID usuario);
    List<NFE> findAllByOrderByCriadoAsc();

}
