package com.silcom.manager.domain.event;

import com.silcom.manager.domain.model.ClienteVenda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VendaConfirmadaEvent {
    
    private ClienteVenda venda;
    
}
