package net.guilhermejr.sistema.supermercadoservice.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Retorna Unidade")
public class UnidadeResponse {

    @Schema(description = "Id", example = "bf46be73-815e-410b-b787-cb48c35f8b1c")
    private UUID id;

    @Schema(description = "Descrição", example = "KG")
    private String descricao;

    @Schema(description = "Data da criação do registro", example = "2022-06-04T17:13:47.218729")
    private LocalDateTime criado;

}
