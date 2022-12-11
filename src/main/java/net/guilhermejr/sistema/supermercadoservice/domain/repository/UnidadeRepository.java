package net.guilhermejr.sistema.supermercadoservice.domain.repository;

import net.guilhermejr.sistema.supermercadoservice.domain.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, UUID> {

    Unidade findByDescricao(String descricao);

}
