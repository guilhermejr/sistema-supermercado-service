package net.guilhermejr.sistema.supermercadoservice.api.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoResponse {

    private String ean;
    private String nome;
    private UnidadeResponse unidade;

}
