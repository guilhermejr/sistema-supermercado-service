package net.guilhermejr.sistema.supermercadoservice.api.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Request para inserir compra")
public class URLRequest {

    @NotBlank
    @Schema(description = "url", example = "URL da compra")
    private String url;

}
