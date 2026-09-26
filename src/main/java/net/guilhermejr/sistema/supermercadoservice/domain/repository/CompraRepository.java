package net.guilhermejr.sistema.supermercadoservice.domain.repository;

import net.guilhermejr.sistema.supermercadoservice.domain.entity.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, UUID> {

    Optional<Compra> findByChaveDeAcesso(String chave);

    Page<Compra> findAllByUsuario(UUID usuario, Pageable paginacao);

    /** Busca por id restringindo ao dono: id de outro usuário não é encontrado. */
    Optional<Compra> findByIdAndUsuario(UUID id, UUID usuario);

}
