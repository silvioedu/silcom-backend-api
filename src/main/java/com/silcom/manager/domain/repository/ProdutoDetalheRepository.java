package com.silcom.manager.domain.repository;

import java.util.List;
import java.util.Optional;

import com.silcom.manager.domain.model.ProdutoDetalhe;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDetalheRepository extends PagingAndSortingRepository<ProdutoDetalhe, Long> {
 
    @Cacheable("produtodetalhes")
    List<ProdutoDetalhe> findAllByOrderByNomeAsc();

    List<ProdutoDetalhe> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsBySiglaIgnoreCase(String sigla);
    Optional<ProdutoDetalhe> findByNome(String nome);
}
