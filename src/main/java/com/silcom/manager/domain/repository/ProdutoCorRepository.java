package com.silcom.manager.domain.repository;

import java.util.List;
import java.util.Optional;

import com.silcom.manager.domain.model.ProdutoCor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoCorRepository extends PagingAndSortingRepository<ProdutoCor, Long> {
 
    @Cacheable("produtocores")
    List<ProdutoCor> findAllByOrderByNomeAsc();

    List<ProdutoCor> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsBySiglaIgnoreCase(String sigla);
    Optional<ProdutoCor> findByNome(String nome);
}
