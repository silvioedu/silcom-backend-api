package com.silcom.manager.domain.repository;

import java.util.List;
import java.util.Optional;

import com.silcom.manager.domain.model.ProdutoTipo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoTipoRepository extends PagingAndSortingRepository<ProdutoTipo, Long> {
 
    @Cacheable("produtotipos")
    List<ProdutoTipo> findAllByOrderByNomeAsc();

    List<ProdutoTipo> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsBySiglaIgnoreCase(String sigla);
    Optional<ProdutoTipo> findByNome(String nome);
}
