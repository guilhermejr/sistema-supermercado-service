package net.guilhermejr.sistema.supermercadoservice.api.mapper;

import net.guilhermejr.sistema.supermercadoservice.api.response.CompraListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.api.response.CompraResponse;
import net.guilhermejr.sistema.supermercadoservice.config.ModelMapperConfig;
import net.guilhermejr.sistema.supermercadoservice.domain.entity.Compra;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CompraMapper extends ModelMapperConfig {

    public CompraResponse mapObject(Compra compra) {
        return this.mapObject(compra, CompraResponse.class);
    }

    public Page<CompraListagemResponse> mapPage(Page<Compra> compras) {
        return this.mapPage(compras, CompraListagemResponse.class);
    }

}
