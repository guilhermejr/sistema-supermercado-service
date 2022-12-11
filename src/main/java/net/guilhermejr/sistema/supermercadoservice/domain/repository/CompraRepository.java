package net.guilhermejr.sistema.supermercadoservice.domain.repository;

import net.guilhermejr.sistema.supermercadoservice.domain.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, UUID> {

    Optional<Compra> findByChaveDeAcesso(String chave);

}
