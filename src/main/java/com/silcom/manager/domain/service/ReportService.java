package com.silcom.manager.domain.service;

import java.util.List;

import com.silcom.manager.api.assembler.output.VendaReportOutputAssembler;
import com.silcom.manager.api.dto.output.report.VendaReportDTO;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.model.ClienteEndereco;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.model.ClienteVendaItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    
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

    public VendaReportDTO getVendaSummary(Long clienteId, Long vendaId) {
        final ClienteVenda clienteVenda = vendaService.findByVendaId(vendaId);
        final List<ClienteVendaItem> itensVenda = vendaItemService.findAllByVenda(vendaId);
        final List<ClienteDocumento> clienteDocumentos = documentoService.findAll(clienteId);
        final List<ClienteContato> clienteContatos = contatoService.findAll(clienteId);
        final List<ClienteEndereco> clienteEnderecos = enderecoService.findAll(clienteId);

        return vendaReportOutputAssembler.toDTO(clienteVenda, itensVenda, clienteDocumentos, clienteContatos, clienteEnderecos);
    }

}
