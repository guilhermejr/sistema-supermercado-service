package net.guilhermejr.sistema.supermercadoservice.api.request;

import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class URLRequest {

    @NotBlank
    private String url;

}
