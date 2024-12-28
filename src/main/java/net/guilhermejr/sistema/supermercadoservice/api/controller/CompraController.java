package net.guilhermejr.sistema.supermercadoservice.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.supermercadoservice.api.response.CompraListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.api.response.CompraResponse;
import net.guilhermejr.sistema.supermercadoservice.api.response.SupermercadoListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.exception.dto.ErrorDefaultDTO;
import net.guilhermejr.sistema.supermercadoservice.service.CompraService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
    @GetMapping
    public ResponseEntity<Page<CompraListagemResponse>> retornar(@PageableDefault(page = 0, size = 10, sort = "data", direction = Sort.Direction.DESC) Pageable paginacao) {

        log.info("Retornando compras");
        Page<CompraListagemResponse> compraListagemResponses = compraService.retornar(paginacao);
        return ResponseEntity.status(HttpStatus.OK).body(compraListagemResponses);

    }

    // --- RetornarUm ---------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> retornarUm(@PathVariable UUID id) {

        log.info("Recuperando uma compra: {}", id);
        CompraResponse compraResponse = compraService.retornarUm(id);
        return ResponseEntity.status(HttpStatus.OK).body(compraResponse);

    }

}
