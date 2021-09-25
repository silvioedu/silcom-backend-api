package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.ClienteEndereco;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteEnderecoRepository extends PagingAndSortingRepository<ClienteEndereco, Long> {
 
    List<ClienteEndereco> findAll();

}
