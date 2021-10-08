package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.LembreteTipo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LembreteTipoRepository extends PagingAndSortingRepository<LembreteTipo, Long> {
 
    @Cacheable("lembretetipos")
    List<LembreteTipo> findAllByOrderByNomeAsc();

    List<LembreteTipo> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);

}
