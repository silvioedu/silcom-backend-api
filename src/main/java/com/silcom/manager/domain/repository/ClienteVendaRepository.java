package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.ClienteVenda;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteVendaRepository extends PagingAndSortingRepository<ClienteVenda, Long> {
 
    List<ClienteVenda> findAllByClienteIdOrderByDataCriacaoAsc(Long clienteId);

    boolean existsByFormaPagamentoTipoId(Long formaPagamentoTipo);

    void deleteByClienteId(Long clienteId);
}
