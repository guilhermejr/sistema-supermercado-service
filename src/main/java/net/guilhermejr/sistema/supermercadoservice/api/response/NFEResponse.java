package net.guilhermejr.sistema.supermercadoservice.api.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NFEResponse {

    private String url;
    private Boolean retornou;
    private String mensagem;
    private String data;
    private String total;
    private String cnpj;
    private String ie;
    private String nome;
    private String chaveDeAcesso;
    private String informacoesComplementares;
    private List<ProdutoNFEResponse> produtos;

}
