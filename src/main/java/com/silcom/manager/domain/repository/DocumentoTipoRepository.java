package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.DocumentoTipo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoTipoRepository extends PagingAndSortingRepository<DocumentoTipo, Long> {
 
    @Cacheable("documentotipos")
    List<DocumentoTipo> findAllByOrderByNomeAsc();

    List<DocumentoTipo> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);

}
