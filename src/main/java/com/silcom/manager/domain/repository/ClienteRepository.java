package com.silcom.manager.domain.repository;

import java.util.List;

import com.silcom.manager.domain.model.Cliente;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
 
    @Cacheable("clientes")
    List<Cliente> findAllByOrderByRazaoSocialAsc();

    List<Cliente> findByRazaoSocialContainingIgnoreCase(String razaoSocial);
    boolean existsByRazaoSocialIgnoreCase(String razaoSocial);

}
