package net.guilhermejr.sistema.supermercadoservice.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Retorno de Produto")
public class ProdutoResponse {

    @Schema(description = "Código de barras", example = "7896082804123")
    private String ean;

    @Schema(description = "Nome", example = "SACO LIXO DOVER ROL SUP F 100L C10")
    private String nome;

    @Schema(description = "Unidade", example = "UN")
    private UnidadeResponse unidade;

}
