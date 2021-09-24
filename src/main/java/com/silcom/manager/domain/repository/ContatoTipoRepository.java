package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.ContatoTipo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoTipoRepository extends PagingAndSortingRepository<ContatoTipo, Long> {
 
    @Cacheable("contatotipos")
    List<ContatoTipo> findAllByOrderByNomeAsc();

    List<ContatoTipo> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);

}
