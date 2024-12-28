package net.guilhermejr.sistema.supermercadoservice.api.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupermercadoListagemResponse {

    private UUID id;
    private String nome;

}
