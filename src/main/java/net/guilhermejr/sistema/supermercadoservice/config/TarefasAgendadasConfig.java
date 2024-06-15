package net.guilhermejr.sistema.supermercadoservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.supermercadoservice.api.request.URLRequest;
import net.guilhermejr.sistema.supermercadoservice.domain.entity.NFE;
import net.guilhermejr.sistema.supermercadoservice.domain.repository.NFERepository;
import net.guilhermejr.sistema.supermercadoservice.service.CompraService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class TarefasAgendadasConfig {

    private final CompraService compraService;
    private final NFERepository nfeRepository;

    @Scheduled(fixedDelayString = "${sistema.nfe-ba-service.tempoProcessamentoNFE}")
    public void processaNFE() {

        log.info("Verificando se existe NFE a ser processada");
        List<NFE> nfeListagemResponses = nfeRepository.findAllByOrderByCriadoAsc();

        nfeListagemResponses.forEach(nfe -> {

            URLRequest urlRequest = URLRequest.builder().url(nfe.getUrl()).build();

            try {

                compraService.inserir(urlRequest, nfe.getUsuario());

                log.info("Removido NFE pois foi processada: {}", urlRequest.getUrl());
                nfeRepository.delete(nfe);

            } catch (Exception e) {

                if (e.getMessage().equals("Compra já realizada")) {

                    log.info("Removendo NFE pois ela já foi inserida anteriormente: {}", urlRequest.getUrl());
                    nfeRepository.delete(nfe);

                } else {

                    log.error("Erro ao processar NFE: {}", e.getMessage());
                    Integer tentativas = nfe.getTentativas();
                    tentativas++;
                    nfe.setTentativas(tentativas);
                    nfeRepository.save(nfe);

                }

            }

        });

    }


}
