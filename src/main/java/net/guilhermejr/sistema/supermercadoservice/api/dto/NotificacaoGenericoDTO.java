package net.guilhermejr.sistema.supermercadoservice.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificacaoGenericoDTO {

    private String titulo;
    private String mensagem;

}
