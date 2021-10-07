package com.silcom.manager.domain.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import com.silcom.manager.domain.exception.ResourceNotFoundException;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.repository.ClienteVendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteVendaService {
    
    private static final String ID_NOT_FOUND = "Venda id %d não encontrado para o cliente id %d";

    @Autowired
    private ClienteVendaRepository vendaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteVendaItemService clienteVendaItemService;

    @Autowired
    private FormaPagamentoTipoService formaPagamentoTipoService;

    public boolean existsByFormaPagamentoTipoId(Long formaPagamentoTipoId) {
        return vendaRepository.existsByFormaPagamentoTipoId(formaPagamentoTipoId);
    }

    public List<ClienteVenda> findAll(final Long clienteId) {
        clienteService.findById(clienteId);
        return vendaRepository.findAllByClienteIdOrderByDataCriacaoAsc(clienteId);
    }

    private ClienteVenda findByVendaId(final Long id) {
        return vendaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id, 0)
            ));
    }

    public ClienteVenda findById(final Long clienteId, final Long id) {
        clienteService.findById(clienteId);

        return vendaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format(ID_NOT_FOUND, id, clienteId)
            ));
    }

    @Transactional
    public ClienteVenda insert(final Long clienteId, final ClienteVenda venda) {
        ClienteVenda vendaFormatado = createVenda(venda, clienteId);
        return vendaRepository.save(vendaFormatado);
    }

    @Transactional
    public void delete(final Long clienteId, final Long id) {
        clienteVendaItemService.deleteByClienteVendaId(id);
        vendaRepository.delete(this.findById(clienteId, id));
    }

    @Transactional
    public ClienteVenda update(Long clienteId, Long vendaId, ClienteVenda venda) {
        ClienteVenda vendaRecovered = this.findById(clienteId, vendaId);
 
        venda.setId(vendaRecovered.getId());
        venda.setCliente(vendaRecovered.getCliente());
        venda.setFormaPagamentoTipo(formaPagamentoTipoService.findById(venda.getFormaPagamentoTipo().getId()));
        venda.setDataCriacao(vendaRecovered.getDataCriacao());
        return vendaRepository.save(venda);
    }

    private ClienteVenda createVenda(ClienteVenda venda, Long clienteId) {
        venda.setCliente(clienteService.findById(clienteId));
        venda.setFormaPagamentoTipo(formaPagamentoTipoService.findById(venda.getFormaPagamentoTipo().getId()));
        return venda;
    }

    @Transactional
    public void deleteByClienteId(Long id) {

        this.findAll(id).stream()
            .forEach(item -> clienteVendaItemService.deleteByClienteVendaId(item.getId()));
        
        vendaRepository.deleteByClienteId(id);
    }

    @Transactional
    public void updateValorTotal(Long clienteVendaId) {
        ClienteVenda venda = this.findByVendaId(clienteVendaId);

        BigDecimal total = this.clienteVendaItemService.findAllByVenda(clienteVendaId).stream()
            .map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        venda.setValorTotal(total);
        this.update(venda.getCliente().getId(), clienteVendaId, venda);
    }

}
