package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.Produto;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
 
    @Cacheable("produtos")
    List<Produto> findAllByOrderByIdAsc();

    boolean existsByCodigoIgnoreCase(String codigo);

    boolean existsByCorId(Long id);
    boolean existsByComplementoId(Long id);
    boolean existsByDetalheId(Long id);
    boolean existsByFabricanteId(Long id);
    boolean existsByTipoId(Long id);
}