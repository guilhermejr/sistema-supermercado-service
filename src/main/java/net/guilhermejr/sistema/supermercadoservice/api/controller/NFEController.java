package net.guilhermejr.sistema.supermercadoservice.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.supermercadoservice.api.request.URLRequest;
import net.guilhermejr.sistema.supermercadoservice.api.response.NFEListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.service.NFEService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyRole('SUPERMERCADO')")
@RequestMapping("/nfe")
public class NFEController {

    private final NFEService NFEService;

    @PostMapping
    public ResponseEntity<String> inserir(@Valid @RequestBody URLRequest urlRequest) {

        log.info("Inserindo NFE: {}", urlRequest.getUrl());
        NFEService.inserir(urlRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("NFE inserida com sucesso");

    }

    @GetMapping
    public ResponseEntity<List<NFEListagemResponse>> retornar() {

        log.info("Retornando compras");
        List<NFEListagemResponse> nfeListagemResponses = NFEService.retornar();
        return ResponseEntity.status(HttpStatus.OK).body(nfeListagemResponses);

    }

}
