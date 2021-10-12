package com.silcom.manager.api.controller;

import java.util.List;
import java.util.Objects;

import com.silcom.manager.api.assembler.output.VendaReportOutputAssembler;
import com.silcom.manager.api.dto.output.report.VendaReportDTO;
import com.silcom.manager.domain.exception.ReportException;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.model.ClienteEndereco;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.model.ClienteVendaItem;
import com.silcom.manager.domain.service.ClienteContatoService;
import com.silcom.manager.domain.service.ClienteDocumentoService;
import com.silcom.manager.domain.service.ClienteEnderecoService;
import com.silcom.manager.domain.service.ClienteVendaItemService;
import com.silcom.manager.domain.service.ClienteVendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {
    
    @Autowired
    private ClienteVendaService vendaService;

    @Autowired
    private ClienteVendaItemService vendaItemService;

    @Autowired
    private ClienteDocumentoService documentoService;

    @Autowired
    private ClienteContatoService contatoService;

    @Autowired
    private ClienteEnderecoService enderecoService;

    @Autowired
    private VendaReportOutputAssembler vendaReportOutputAssembler;

    @GetMapping("/vendas/{id}")
    public VendaReportDTO getVenda(@PathVariable(required = true) Long id) {
        final ClienteVenda clienteVenda = vendaService.findByVendaId(id);

        if(Objects.isNull(clienteVenda.getCliente()) || Objects.isNull(clienteVenda.getCliente().getId())) {
            throw new ReportException("Problemas ao gerar o relatório, venda não possui cliente");
        }

        final Long clienteId = clienteVenda.getCliente().getId();
        final List<ClienteVendaItem> itensVenda = vendaItemService.findAllByVenda(id);
        final List<ClienteDocumento> clienteDocumentos = documentoService.findAll(clienteId);
        final List<ClienteContato> clienteContatos = contatoService.findAll(clienteId);
        final List<ClienteEndereco> clienteEnderecos = enderecoService.findAll(clienteId);

        return vendaReportOutputAssembler.toDTO(clienteVenda, itensVenda, clienteDocumentos, clienteContatos, clienteEnderecos);
    }

}