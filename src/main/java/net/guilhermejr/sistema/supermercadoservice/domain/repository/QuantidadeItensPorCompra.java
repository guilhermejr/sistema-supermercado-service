package net.guilhermejr.sistema.supermercadoservice.domain.repository;

import java.util.UUID;

/** Quantidade de itens de uma compra, usada para montar a listagem sem carregar os itens. */
public interface QuantidadeItensPorCompra {

    UUID getCompra();

    Long getQuantidade();

}
