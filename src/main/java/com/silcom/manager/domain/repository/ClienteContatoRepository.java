package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.ClienteContato;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteContatoRepository extends PagingAndSortingRepository<ClienteContato, Long> {
 
    List<ClienteContato> findAllByClienteId(Long clienteId);

    boolean existsByContatoTipoId(Long contatoTipoId);

}
