package net.guilhermejr.sistema.supermercadoservice.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.supermercadoservice.api.request.URLRequest;
import net.guilhermejr.sistema.supermercadoservice.api.response.CompraListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.api.response.CompraResponse;
import net.guilhermejr.sistema.supermercadoservice.api.response.NFEResponse;
import net.guilhermejr.sistema.supermercadoservice.api.response.SupermercadoListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.exception.dto.ErrorDefaultDTO;
import net.guilhermejr.sistema.supermercadoservice.exception.dto.ErrorRequestDTO;
import net.guilhermejr.sistema.supermercadoservice.service.CompraService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyRole('SUPERMERCADO')")
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    // --- Retornar -----------------------------------------------------------
    @Operation(summary = "Retorna compras", responses = {
            @ApiResponse(responseCode = "200", description = "OK",content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SupermercadoListagemResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDefaultDTO.class)))
    })
    @GetMapping
    public ResponseEntity<Page<CompraListagemResponse>> retornar(@PageableDefault(page = 0, size = 10, sort = "data", direction = Sort.Direction.DESC) Pageable paginacao) {

        log.info("Retornando compras");
        Page<CompraListagemResponse> compraListagemResponses = compraService.retornar(paginacao);
        return ResponseEntity.status(HttpStatus.OK).body(compraListagemResponses);

    }

    // --- RetornarUm ---------------------------------------------------------
    @Operation(summary = "Retorna uma compra", responses = {
            @ApiResponse(responseCode = "200", description = "OK",content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompraResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDefaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDefaultDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> retornarUm(@PathVariable UUID id) {

        log.info("Recuperando uma compra: {}", id);
        CompraResponse compraResponse = compraService.retornarUm(id);
        return ResponseEntity.status(HttpStatus.OK).body(compraResponse);

    }

    // --- Inserir ------------------------------------------------------------
    @Operation(summary = "Insere uma compra", responses = {
            @ApiResponse(responseCode = "201", description = "OK",content = @Content(mediaType = "application/json", schema = @Schema(implementation = NFEResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorRequestDTO.class)))
    })
    @PostMapping
    public ResponseEntity<NFEResponse> inserir(@Valid @RequestBody URLRequest urlRequest) {

        log.info("Inserindo compra: {}", urlRequest.getUrl());
        NFEResponse nfeResponse = compraService.inserir(urlRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nfeResponse);

    }
}
