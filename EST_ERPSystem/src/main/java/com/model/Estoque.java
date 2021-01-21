package com.model;

import java.util.Date;

public class Estoque {

	private int quantidade;
	private Date data;
	
	public Estoque() {
		
	}
	public Estoque(int quantidade, Date data) {
		this.quantidade = quantidade;
		this.data = data;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
	  return String.valueOf("Quantidade: " + this.getQuantidade()) + " Data: " + this.getData();
	}
	
	
}
