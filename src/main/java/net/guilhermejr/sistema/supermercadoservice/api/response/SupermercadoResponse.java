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
@Schema(description = "Retorna supermercado")
public class SupermercadoResponse {

    @Schema(description = "Id", example = "bf46be73-815e-410b-b787-cb48c35f8b1c")
    private UUID id;

    @Schema(description = "CNPJ", example = "06.337.087/0015-79")
    private String cnpj;

    @Schema(description = "Inscrição Estadual", example = "176848181")
    private String inscricaoEstadual;

    @Schema(description = "Nome", example = "REDEMIX SUPERMERCADOS")
    private String nome;

    @Schema(description = "Informação complementares", example = "Qualquer coisa aqui")
    private String informacoesComplementares;

    @Schema(description = "Data da criação do registro", example = "2022-06-04T17:13:47.218729")
    private LocalDateTime criado;

}
