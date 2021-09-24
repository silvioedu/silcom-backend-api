package com.silcom.manager.domain.repository;

import java.util.List;
import java.util.Optional;

import com.silcom.manager.domain.model.ProdutoFabricante;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoFabricanteRepository extends PagingAndSortingRepository<ProdutoFabricante, Long> {
 
    @Cacheable("produtofabricantes")
    List<ProdutoFabricante> findAllByOrderByNomeAsc();

    List<ProdutoFabricante> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsBySiglaIgnoreCase(String sigla);
    Optional<ProdutoFabricante> findByNome(String nome);
}
