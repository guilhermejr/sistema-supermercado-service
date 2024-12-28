package net.guilhermejr.sistema.supermercadoservice.api.response;

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
public class CompraResponse {

    private UUID id;
    private LocalDateTime data;
    private String chaveDeAcesso;
    private String url;
    private BigDecimal total;
    private LocalDateTime criado;
    private SupermercadoResponse supermercado;
    private List<ItemResponse> items;

}
