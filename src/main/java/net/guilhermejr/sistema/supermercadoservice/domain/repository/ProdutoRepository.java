package net.guilhermejr.sistema.supermercadoservice.domain.repository;

import net.guilhermejr.sistema.supermercadoservice.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Produto findByEan(String ean);

    Produto findByEanAndNome(String ean, String nome);

}
