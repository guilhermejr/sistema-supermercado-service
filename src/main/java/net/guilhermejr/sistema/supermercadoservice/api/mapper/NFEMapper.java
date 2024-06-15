package net.guilhermejr.sistema.supermercadoservice.api.mapper;

import net.guilhermejr.sistema.supermercadoservice.api.response.NFEListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.config.ModelMapperConfig;
import net.guilhermejr.sistema.supermercadoservice.domain.entity.NFE;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NFEMapper extends ModelMapperConfig {

    public List<NFEListagemResponse> mapList(List<NFE> nfe) {
        return this.mapList(nfe, NFEListagemResponse.class);
    }

}
