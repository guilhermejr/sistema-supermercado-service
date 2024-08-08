package net.guilhermejr.sistema.supermercadoservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.supermercadoservice.api.dto.NotificacaoGenericoDTO;
import net.guilhermejr.sistema.supermercadoservice.api.request.URLRequest;
import net.guilhermejr.sistema.supermercadoservice.api.response.NFEResponse;
import net.guilhermejr.sistema.supermercadoservice.client.NotificacaoClient;
import net.guilhermejr.sistema.supermercadoservice.domain.entity.NFE;
import net.guilhermejr.sistema.supermercadoservice.domain.repository.NFERepository;
import net.guilhermejr.sistema.supermercadoservice.service.CompraService;
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
    private final NotificacaoClient notificacaoClient;

    @Scheduled(fixedDelayString = "${sistema.nfe-ba-service.tempoProcessamentoNFE}")
    public void processaNFE() {

        log.info("Verificando se existe NFE a ser processada");
        List<NFE> nfeListagemResponses = nfeRepository.findAllByOrderByCriadoAsc();

        if (nfeListagemResponses.isEmpty()) {

            log.info("Nenhuma NFE a ser processada");

        } else {

            nfeListagemResponses.forEach(nfe -> {

                URLRequest urlRequest = URLRequest.builder().url(nfe.getUrl()).build();

                try {

                    NFEResponse nfeResponse = compraService.inserir(urlRequest, nfe.getUsuario());
                    notificacaoClient.enviarNotificacaoNFE(nfeResponse);
                    log.info("Removido NFE pois foi processada: {}", urlRequest.getUrl());
                    nfeRepository.delete(nfe);

                } catch (Exception e) {

                    if (e.getMessage().equals("Compra já realizada")) {

                        NotificacaoGenericoDTO notificacaoGenericoDTO = NotificacaoGenericoDTO
                                .builder()
                                .titulo("Nota fiscal já inserida")
                                .mensagem("Nota fiscal já existe na base de dados")
                                .build();
                        notificacaoClient.enviarNotificacaoGenerica(notificacaoGenericoDTO);
                        log.info("Removendo NFE pois ela já foi inserida anteriormente: {}", urlRequest.getUrl());
                        nfeRepository.delete(nfe);

                    } else {

                        log.error("Erro ao processar NFE: {}", e.getMessage());
                        Integer tentativas = nfe.getTentativas();
                        tentativas++;
                        if (tentativas % 24 == 0) {
                            NotificacaoGenericoDTO notificacaoGenericoDTO = NotificacaoGenericoDTO
                                    .builder()
                                    .titulo("Nota fiscal ainda não inserida")
                                    .mensagem("Problemas ao tentar buscar os dados da NFE " + nfe.getUrl())
                                    .build();
                            notificacaoClient.enviarNotificacaoGenerica(notificacaoGenericoDTO);
                        }
                        nfe.setTentativas(tentativas);
                        nfeRepository.save(nfe);

                    }

                }

            });

        }

    }

}
