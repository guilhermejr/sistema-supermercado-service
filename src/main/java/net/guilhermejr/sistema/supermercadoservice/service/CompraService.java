package net.guilhermejr.sistema.supermercadoservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.supermercadoservice.api.mapper.CompraMapper;
import net.guilhermejr.sistema.supermercadoservice.api.request.URLRequest;
import net.guilhermejr.sistema.supermercadoservice.api.response.CompraListagemResponse;
import net.guilhermejr.sistema.supermercadoservice.api.response.CompraResponse;
import net.guilhermejr.sistema.supermercadoservice.api.response.NFEResponse;
import net.guilhermejr.sistema.supermercadoservice.client.NFEBAClient;
import net.guilhermejr.sistema.supermercadoservice.config.security.AuthenticationCurrentUserService;
import net.guilhermejr.sistema.supermercadoservice.domain.entity.*;
import net.guilhermejr.sistema.supermercadoservice.domain.repository.*;
import net.guilhermejr.sistema.supermercadoservice.exception.ExceptionDefault;
import net.guilhermejr.sistema.supermercadoservice.exception.ExceptionNotFound;
import net.guilhermejr.sistema.supermercadoservice.util.ConverteStringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class CompraService {

    private final NFEBAClient nfebaClient;
    private final SupermercadoRepository supermercadoRepository;
    private final UnidadeRepository unidadeRepository;
    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;
    private final ItemRepository itemRepository;
    private final ConverteStringUtil converteStringUtil;
    private final CompraMapper compraMapper;
    private final AuthenticationCurrentUserService authenticationCurrentUserService;

    // --- Inserir ------------------------------------------------------------
    public NFEResponse inserir(URLRequest urlRequest) {

        // --- Quebra url ---
        String busca = urlRequest.getUrl().split("=")[1];

        // --- Busca dados no site da secretaria da fazenda da bahia ---
        NFEResponse nfeResponse = nfebaClient.buscar(busca);

        // --- Verifica se já foi inserida ---
        if (compraRepository.findByChaveDeAcesso(nfeResponse.getChaveDeAcesso()).isPresent()) {
            log.error("Compra já realizada {}", nfeResponse.getChaveDeAcesso());
            throw new ExceptionDefault("Compra já realizada");
        }

        // --- Supermercado ---
        Supermercado supermercado = supermercadoRepository.findByCnpj(nfeResponse.getCnpj());
        if (supermercado == null) {
            supermercado = supermercadoRepository.save(Supermercado.builder()
                    .cnpj(nfeResponse.getCnpj())
                    .inscricaoEstadual(nfeResponse.getIe())
                    .nome(nfeResponse.getNome())
                    .informacoesComplementares(nfeResponse.getInformacoesComplementares())
                    .criado(LocalDateTime.now(ZoneId.of("UTC")))
                    .build());
            supermercadoRepository.save(supermercado);
            log.info("Supermercado cadastrado {}", nfeResponse.getNome());
        }

        UUID usuario = authenticationCurrentUserService.getCurrentUser().getId();

        // --- Compra ---
        Compra compra = Compra.builder()
                .supermercado(supermercado)
                .data(converteStringUtil.toLocalDateTime(nfeResponse.getData()))
                .chaveDeAcesso(nfeResponse.getChaveDeAcesso())
                .total(converteStringUtil.toBigDecimal(nfeResponse.getTotal()))
                .url(nfeResponse.getUrl())
                .criado(LocalDateTime.now(ZoneId.of("UTC")))
                .usuario(usuario)
                .build();
        compraRepository.save(compra);
        log.info("Compra cadastrada {} - {}", nfeResponse.getData(), nfeResponse.getChaveDeAcesso());

        // --- Produtos ---
        nfeResponse.getProdutos().forEach(p -> {

            // --- Produto ---
            Produto produto;
            if (p.getEan().matches("[0-9]+")) {
                produto = produtoRepository.findByEan(p.getEan());
            } else {
                produto = produtoRepository.findByEanAndNome(p.getEan(), p.getNome());
            }

            if (produto == null) {

                // --- Unidade ---
                Unidade unidade = unidadeRepository.findByDescricao(p.getUnidade());
                if (unidade == null) {
                    unidade = Unidade.builder()
                            .descricao(p.getUnidade())
                            .criado(LocalDateTime.now(ZoneId.of("UTC")))
                            .build();
                    unidadeRepository.save(unidade);
                    log.info("Unidade cadastrada {}", p.getUnidade());
                }

                produto = Produto.builder()
                        .ean(p.getEan())
                        .nome(p.getNome())
                        .unidade(unidade)
                        .criado(LocalDateTime.now(ZoneId.of("UTC")))
                        .build();
                produtoRepository.save(produto);
                log.info("Produto cadastrado {}", p.getNome());
            }

            // --- Item ---
            Item item = Item.builder()
                    .compra(compra)
                    .produto(produto)
                    .qtd(converteStringUtil.toBigDecimal(p.getQtd()))
                    .valor(converteStringUtil.toBigDecimal(p.getValor()))
                    .build();
            itemRepository.save(item);
            log.info("Item da compra cadastrado {} - {} - {} - {}", nfeResponse.getData(), p.getNome(), p.getQtd(), p.getValor());

        });

        return nfeResponse;

    }

    // --- Retornar -----------------------------------------------------------
    public Page<CompraListagemResponse> retornar(Pageable paginacao) {

        Page<Compra> compras = compraRepository.findAll(paginacao);
        return compraMapper.mapPage(compras);

    }

    // --- RetornarUm ---------------------------------------------------------
    public CompraResponse retornarUm(UUID id) {

        Compra compra = compraRepository.findById(id).orElseThrow(() -> {
            log.error("Compra: {} - Não encontrada", id);
            throw new ExceptionNotFound("Compra: "+ id +" - Não encontrada");
        });

        return compraMapper.mapObject(compra);

    }
}
