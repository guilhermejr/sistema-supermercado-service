package net.guilhermejr.sistema.supermercadoservice.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Retorno de listagem de compras")
public class CompraListagemResponse {

    @Schema(description = "Id", example = "bf46be73-815e-410b-b787-cb48c35f8b1c")
    private UUID id;

    @Schema(description = "Data da compra", example = "2022-06-04T17:13:47.218729")
    private LocalDateTime data;

    @Schema(description = "Valor total da compra", example = "319.79")
    private BigDecimal total;

    @Schema(description = "Supermercado")
    private SupermercadoListagemResponse supermercado;

}
