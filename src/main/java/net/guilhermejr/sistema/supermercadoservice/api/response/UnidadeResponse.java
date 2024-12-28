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
public class UnidadeResponse {

    private UUID id;
    private String descricao;
    private LocalDateTime criado;

}
