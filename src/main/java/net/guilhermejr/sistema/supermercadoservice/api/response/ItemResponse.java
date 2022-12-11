package net.guilhermejr.sistema.supermercadoservice.api.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import net.guilhermejr.sistema.supermercadoservice.domain.entity.Compra;
import net.guilhermejr.sistema.supermercadoservice.domain.entity.Produto;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Retorna itens")
public class ItemResponse {

    @Schema(description = "Id", example = "bf46be73-815e-410b-b787-cb48c35f8b1c")
    private UUID id;

    @Schema(description = "Produto")
    private ProdutoResponse produto;

    @Schema(description = "Quantidade", example = "1.000")
    private BigDecimal qtd;

    @Schema(description = "Valor", example = "9.79")
    private BigDecimal valor;

}
