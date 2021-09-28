package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.Venda;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends PagingAndSortingRepository<Venda, Long> {
 
    List<Venda> findAllByClienteIdOrderByDataCriacaoAsc(Long clienteId);

    boolean existsByFormaPagamentoTipoId(Long formaPagamentoTipo);

    void deleteByClienteId(Long clienteId);
}
