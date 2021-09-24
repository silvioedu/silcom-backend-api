package com.silcom.manager.domain.repository;

import java.util.List;
import java.util.Optional;

import com.silcom.manager.domain.model.ProdutoComplemento;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoComplementoRepository extends PagingAndSortingRepository<ProdutoComplemento, Long> {
 
    @Cacheable("produtocomplementos")
    List<ProdutoComplemento> findAllByOrderByNomeAsc();

    List<ProdutoComplemento> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsBySiglaIgnoreCase(String sigla);
    Optional<ProdutoComplemento> findByNome(String nome);
}
