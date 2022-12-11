package net.guilhermejr.sistema.supermercadoservice.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Retorna compra")
public class CompraResponse {

    @Schema(description = "Id", example = "bf46be73-815e-410b-b787-cb48c35f8b1c")
    private UUID id;

    @Schema(description = "Data da compra", example = "2022-06-04T17:13:47.218729")
    private LocalDateTime data;

    @Schema(description = "Chave de acesso", example = "2922 0602 2129 3700 2876 6500 2000 0992 6211 3318 5957")
    private String chaveDeAcesso;

    @Schema(description = "URL da nota fiscal", example = "http://nfe.sefaz.ba.gov.br/servicos/nfce/qrcode.aspx?p=29220602212937002876650020000992621133185957|2|1|1|D6587D81BCBA198CAEC98C9AC667E88094D93170")
    private String url;

    @Schema(description = "Valor total da compra", example = "319.79")
    private BigDecimal total;

    @Schema(description = "Data da criação do registro", example = "2022-06-04T17:13:47.218729")
    private LocalDateTime criado;

    @Schema(description = "Supermercado")
    private SupermercadoResponse supermercado;

    @Schema(description = "Itens")
    private List<ItemResponse> items;

}
