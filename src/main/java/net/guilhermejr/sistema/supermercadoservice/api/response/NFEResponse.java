package net.guilhermejr.sistema.supermercadoservice.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Retorno de NFE")
public class NFEResponse {

    @Schema(description = "URL da nota fiscal", example = "http://nfe.sefaz.ba.gov.br/servicos/nfce/qrcode.aspx?p=29220602212937002876650020000992621133185957|2|1|1|D6587D81BCBA198CAEC98C9AC667E88094D93170")
    private String url;

    @Schema(description = "Informa se o retorno é válido ou não", example = "true")
    private Boolean retornou;

    @Schema(description = "Mensagem de caso não tenha conseguido retorno válido", example = "[QRCode v2.00]: Não foi possível obter informações sobre a NFC-e.")
    private String mensagem;

    @Schema(description = "Data da compra", example = "18/06/2022 14:21:53")
    private String data;

    @Schema(description = "Valor total da compra", example = "323,03")
    private String total;

    @Schema(description = "CNPJ do supermercado", example = "02.212.937/0028-76")
    private String cnpj;

    @Schema(description = "Inscrição Estadual do supermercado", example = "164428685")
    private String ie;

    @Schema(description = "Nome do supermercado", example = "HIPERIDEAL LJ 124 - P SHOPPING")
    private String nome;

    @Schema(description = "Chave de acesso da nota fiscal", example = "2922 0602 2129 3700 2876 6500 2000 0992 6211 3318 5957")
    private String chaveDeAcesso;

    @Schema(description = "Informações complementares", example = "Qualquer coisa")
    private String informacoesComplementares;

    @Schema(description = "Lista de produtos comprados")
    private List<ProdutoNFEResponse> produtos;

}
