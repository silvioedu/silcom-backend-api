package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.ClienteContato;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteContatoRepository extends PagingAndSortingRepository<ClienteContato, Long> {
 
    List<ClienteContato> findAllByClienteIdOrderByContatoTipoNomeAsc(Long clienteId);

    boolean existsByContatoTipoId(Long contatoTipoId);

    void deleteByClienteId(Long clienteId);
}
