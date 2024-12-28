package net.guilhermejr.sistema.supermercadoservice.api.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemResponse {

    private UUID id;
    private ProdutoResponse produto;
    private BigDecimal qtd;
    private BigDecimal valor;

}
