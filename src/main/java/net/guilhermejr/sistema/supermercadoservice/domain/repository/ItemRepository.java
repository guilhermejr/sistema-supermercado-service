package net.guilhermejr.sistema.supermercadoservice.domain.repository;

import net.guilhermejr.sistema.supermercadoservice.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    /**
     * Conta os itens de várias compras numa consulta só. Evita o N+1 que sairia de
     * percorrer {@code compra.getItems().size()} para cada linha da listagem.
     */
    @Query("""
            SELECT i.compra.id AS compra, COUNT(i) AS quantidade
            FROM Item i
            WHERE i.compra.id IN :compras
            GROUP BY i.compra.id
            """)
    List<QuantidadeItensPorCompra> contarPorCompras(@Param("compras") Collection<UUID> compras);

}
