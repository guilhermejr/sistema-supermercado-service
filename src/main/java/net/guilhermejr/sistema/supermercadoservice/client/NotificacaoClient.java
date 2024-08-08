package net.guilhermejr.sistema.supermercadoservice.client;

import net.guilhermejr.sistema.supermercadoservice.api.dto.NotificacaoGenericoDTO;
import net.guilhermejr.sistema.supermercadoservice.api.response.NFEResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao-service", url = "http://${sistema.notificacao.host}/notificacao-service/supermercado")
public interface NotificacaoClient {

    @PostMapping("/enviar-notificacao-nfe")
    public ResponseEntity<Void> enviarNotificacaoNFE(@RequestBody NFEResponse nfeResponse);

    @PostMapping("/enviar-notificacao-generica")
    public ResponseEntity<Void> enviarNotificacaoGenerica(@RequestBody NotificacaoGenericoDTO notificacaoGenericoDTO);

}
