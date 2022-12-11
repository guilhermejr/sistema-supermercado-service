package net.guilhermejr.sistema.supermercadoservice.client;

import net.guilhermejr.sistema.supermercadoservice.api.response.NFEResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nfe-ba-service", url = "${sistema.nfe-ba-service.url}")
public interface NFEBAClient {

    @GetMapping("/{nfe}")
    public NFEResponse buscar(@PathVariable String nfe);

}
