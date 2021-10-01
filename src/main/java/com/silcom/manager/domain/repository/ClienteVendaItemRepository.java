package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.ClienteVendaItem;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteVendaItemRepository extends PagingAndSortingRepository<ClienteVendaItem, Long> {
 
    List<ClienteVendaItem> findAllByClienteVendaIdOrderByDataCriacaoAsc(Long clienteVendaId);

    boolean existsByProdutoId(Long produtoId);
    
    void deleteByClienteVendaId(Long clienteVendaId);
}
