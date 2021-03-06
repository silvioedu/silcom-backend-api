package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.ClienteDocumento;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDocumentoRepository extends PagingAndSortingRepository<ClienteDocumento, Long> {
 
    List<ClienteDocumento> findAllByClienteIdOrderByDocumentoTipoNomeAsc(Long clienteId);

    boolean existsByDocumentoTipoId(Long documentoTipoId);

    void deleteByClienteId(Long clienteId);

}
