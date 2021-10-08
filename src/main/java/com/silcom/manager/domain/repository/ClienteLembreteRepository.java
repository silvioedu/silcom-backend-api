package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.ClienteLembrete;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteLembreteRepository extends PagingAndSortingRepository<ClienteLembrete, Long> {
 
    List<ClienteLembrete> findAllByClienteIdOrderByLembreteTipoNomeAsc(Long clienteId);

    boolean existsByLembreteTipoId(Long contatoTipoId);

    void deleteByClienteId(Long clienteId);
}
