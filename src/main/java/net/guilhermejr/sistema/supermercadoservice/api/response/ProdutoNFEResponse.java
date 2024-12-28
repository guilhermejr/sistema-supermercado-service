package net.guilhermejr.sistema.supermercadoservice.api.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoNFEResponse {

    private String ean;
    private String nome;
    private String qtd;
    private String unidade;
    private String valor;

}
