package com.silcom.manager.domain.repository;

import java.util.List;
import java.util.Optional;

import com.silcom.manager.domain.model.Ramo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamoRepository extends PagingAndSortingRepository<Ramo, Long> {
 
    @Cacheable("ramos")
    List<Ramo> findAllByOrderByNomeAsc();

    List<Ramo> findByNomeContaining(String nome);
    boolean existsByNome(String nome);
    Optional<Ramo> findByNome(String nome);
}
