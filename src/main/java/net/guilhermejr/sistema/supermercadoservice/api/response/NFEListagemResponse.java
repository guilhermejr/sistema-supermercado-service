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
public class NFEListagemResponse {

    private UUID id;
    private String url;
    private LocalDateTime criado;
    private Integer tentativas;

}
