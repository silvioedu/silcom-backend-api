package com.silcom.manager.domain.listener;

import java.util.ArrayList;
import java.util.List;

import com.silcom.manager.domain.event.VendaConfirmadaEvent;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.model.EmailMessage;
import com.silcom.manager.domain.service.MailService;
import com.silcom.manager.domain.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class NotificacaoVendaConfirmadaListener {

    @Value("${silcom.mail.sender.noreply}")
    private String sender;

    @Value("${silcom.mail.sender.address}")
    private String recipient;

    @Autowired
    private MailService mailService;
    
    @Autowired
    private ReportService reportService;

    @Async
    @EventListener
    public void aoConfirmarPedido(VendaConfirmadaEvent event) {

        ClienteVenda venda = event.getVenda();

        List<String> recipients = new ArrayList<>();
        recipients.add(recipient);

        EmailMessage message = EmailMessage.builder()
            .sender(sender)
            .recipients(recipients)
            .subject(
                String.format(
                    "[SILCOM] Pedido %d - %s", 
                    venda.getId(),
                    venda.getCliente().getRazaoSocial().toUpperCase())
            )
            .data(reportService.getVendaPDF(venda.getId()))
            .body(
                String.format(
                    "Pedido %d realizado com sucesso.%n", 
                    venda.getId())
            )
            .build();

        mailService.send(message);

    }

}
