package net.guilhermejr.sistema.supermercadoservice.api.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupermercadoResponse {

    private UUID id;
    private String cnpj;
    private String inscricaoEstadual;
    private String nome;
    private String informacoesComplementares;
    private LocalDateTime criado;

}
