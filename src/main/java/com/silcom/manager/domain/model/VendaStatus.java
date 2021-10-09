package com.silcom.manager.domain.model;

public enum VendaStatus {
    
    CRIADO("Criado"),
    FECHADO("Fechado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;
	
	VendaStatus(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
