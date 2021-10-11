package com.silcom.manager.domain.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ClienteVendaItem;
import com.silcom.manager.domain.repository.ClienteVendaItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteVendaItemService {
    
    private static final String ID_NOT_FOUND = "Item de venda id %d não encontrado para a venda id %d";

    @Autowired
    private ClienteVendaItemRepository vendaItemRepository;

    @Autowired
    private ClienteVendaService clienteVendaService;

    @Autowired
    private ProdutoService produtoService;

    public List<ClienteVendaItem> findAll(final Long clienteId, final Long clienteVendaId) {
        clienteVendaService.findById(clienteId, clienteVendaId);
        return vendaItemRepository.findAllByClienteVendaIdOrderByDataCriacaoAsc(clienteVendaId);
    }

    public List<ClienteVendaItem> findAllByVenda(final Long clienteVendaId) {
        return vendaItemRepository.findAllByClienteVendaIdOrderByDataCriacaoAsc(clienteVendaId);
    }

    public ClienteVendaItem findById(final Long clienteId, final Long clienteVendaId, final Long id) {
        clienteVendaService.findById(clienteId, clienteVendaId);

        return vendaItemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id, clienteVendaId)
            ));
    }

    @Transactional
    public ClienteVendaItem insert(final Long clienteId, final Long clienteVendaId, final ClienteVendaItem vendaItem) {
        ClienteVendaItem vendaItemFormatado = createVendaItem(clienteId, clienteVendaId, vendaItem);
        ClienteVendaItem vendaSaved = vendaItemRepository.save(vendaItemFormatado);
        
        return vendaSaved;
    }

    @Transactional
    public void delete(final Long clienteId, final Long clienteVendaId, final Long id) {
        vendaItemRepository.delete(this.findById(clienteId, clienteVendaId, id));
        clienteVendaService.updateValorTotal(clienteVendaId);
    }

    @Transactional
    public ClienteVendaItem update(final Long clienteId, final Long clienteVendaId, final Long id, ClienteVendaItem vendaItem) {
        ClienteVendaItem vendaItemRecovered = this.findById(clienteId, clienteVendaId, id);
        
        createVendaItem(clienteId, clienteVendaId, vendaItem);
        vendaItem.setId(vendaItemRecovered.getId());
        vendaItem.setDataCriacao(vendaItemRecovered.getDataCriacao());

        return vendaItemRepository.save(vendaItem);
    }

    private ClienteVendaItem createVendaItem(final Long clienteId, final Long clienteVendaId, final ClienteVendaItem vendaItem) {
        vendaItem.setClienteVenda(clienteVendaService.findById(clienteId, clienteVendaId));
        vendaItem.setProduto(produtoService.findById(vendaItem.getProduto().getId()));

        if (vendaItem.getValorUnitario().equals(BigDecimal.ZERO)) {
            vendaItem.setValorUnitario(vendaItem.getProduto().getPreco());
        }
        return vendaItem;
    }

    @Transactional
    public void deleteByClienteVendaId(Long id) {
        vendaItemRepository.deleteByClienteVendaId(id);
    }

    public boolean existsByProdutoId(Long produtoId) {
        return vendaItemRepository.existsByProdutoId(produtoId);
    }
}
