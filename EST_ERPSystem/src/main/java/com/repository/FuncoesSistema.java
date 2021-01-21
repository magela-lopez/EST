package com.repository;

import java.util.List;

import com.model.Produto;

public interface FuncoesSistema {

	public void menu();
	public String buscarById();
	public List<Produto> buscarByName();
	public String retirarById();
	
}
