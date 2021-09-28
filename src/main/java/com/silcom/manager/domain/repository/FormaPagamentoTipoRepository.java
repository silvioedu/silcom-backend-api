package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.FormaPagamentoTipo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoTipoRepository extends PagingAndSortingRepository<FormaPagamentoTipo, Long> {
 
    @Cacheable("formapagamentotipos")
    List<FormaPagamentoTipo> findAllByOrderByNomeAsc();

    List<FormaPagamentoTipo> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);

}
