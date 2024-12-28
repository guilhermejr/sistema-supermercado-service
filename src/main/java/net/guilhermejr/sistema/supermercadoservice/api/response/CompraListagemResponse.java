package net.guilhermejr.sistema.supermercadoservice.api.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompraListagemResponse {

    private UUID id;
    private LocalDateTime data;
    private BigDecimal total;
    private SupermercadoListagemResponse supermercado;

}
