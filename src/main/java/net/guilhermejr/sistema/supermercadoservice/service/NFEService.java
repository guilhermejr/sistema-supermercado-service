package net.guilhermejr.sistema.supermercadoservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.supermercadoservice.api.mapper.NFEMapper;
import net.guilhermejr.sistema.supermercadoservice.api.request.URLRequest;
import net.guilhermejr.sistema.supermercadoservice.api.response.NFEListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.config.security.AuthenticationCurrentUserService;
import net.guilhermejr.sistema.supermercadoservice.domain.entity.NFE;
import net.guilhermejr.sistema.supermercadoservice.domain.repository.NFERepository;
import net.guilhermejr.sistema.supermercadoservice.exception.ExceptionDefault;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class NFEService {

    private final NFERepository nfeRepository;
    private final NFEMapper nfeMapper;
    private final AuthenticationCurrentUserService authenticationCurrentUserService;

    // --- Inserir ------------------------------------------------------------
    public void inserir(URLRequest urlRequest) {

        // --- Verifica se já foi inserida ---
        if (nfeRepository.findByUrl(urlRequest.getUrl()).isPresent()) {
            log.error("URL já inserida {}", urlRequest.getUrl());
            throw new ExceptionDefault("URL já inserida");
        }

        UUID usuario = authenticationCurrentUserService.getCurrentUser().getId();

        // --- Insere URL ---
        NFE nfe = NFE.builder()
                .url(urlRequest.getUrl())
                .criado(LocalDateTime.now(ZoneId.of("UTC")))
                .tentativas(0)
                .usuario(usuario)
                .build();
        nfeRepository.save(nfe);

    }

    // --- Retornar -----------------------------------------------------------
    public List<NFEListagemResponse> retornar() {

        UUID usuario = authenticationCurrentUserService.getCurrentUser().getId();

        List<NFE> nfeList = nfeRepository.findAllByUsuarioOrderByCriadoAsc(usuario);
        return nfeMapper.mapList(nfeList);

    }

}
