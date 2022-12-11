package net.guilhermejr.sistema.supermercadoservice.domain.repository;

import net.guilhermejr.sistema.supermercadoservice.domain.entity.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupermercadoRepository extends JpaRepository<Supermercado, UUID> {

    Supermercado findByCnpj(String cnpj);

}
