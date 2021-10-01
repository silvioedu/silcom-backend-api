package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.EnderecoTipo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoTipoRepository extends PagingAndSortingRepository<EnderecoTipo, Long> {
 
    @Cacheable("enderecotipos")
    List<EnderecoTipo> findAllByOrderByNomeAsc();

    List<EnderecoTipo> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);

}
