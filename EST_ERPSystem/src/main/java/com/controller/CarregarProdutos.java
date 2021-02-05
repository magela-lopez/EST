package com.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.model.Arvore;
import com.model.Produto;
import com.opencsv.CSVReader;

public class CarregarProdutos {
	
	private static final String archCSV = System.getProperty("user.dir") + "//Products.csv";
	private List<Produto> produtos = new ArrayList<>();
	private String[] campo = null;
	private Arvore<List<Produto>> arv = new Arvore<List<Produto>>();

	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String[] getCampo() {
		return campo;
	}
	public void setCampo(String[] campo) {
		this.campo = campo;
	}
	
	public Arvore<List<Produto>> getArv() {
		return arv;
	}
	public void setArv(Arvore<List<Produto>> arv) {
		this.arv = arv;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------*/
	

	//Funcionalidade 1
	public void lerCsv() throws IOException {
		
    	CSVReader csvReader = new CSVReader(new FileReader(archCSV));
    	
    		while ((this.campo = csvReader.readNext()) != null) {
    			int rnd = (int) (Math.random()*20)+1; 
    		    this.produtos.add(new Produto (campo[0], campo[1], campo[2], campo[21] , rnd));  
    		}
    		csvReader.close();
    		
     		//Insere os produtos a arvore
    		for (Produto prod : produtos) {
    			this.arv.insert(prod);
    		} 
     	
    		//Lê a lista de produtos
    		Iterator iter = getProdutos().iterator();
			while (iter.hasNext()) {
	  			System.out.println(iter.next().toString());
	  		}
			
			Funcoes func = new Funcoes(getProdutos(), arv);
			func.menu();
	}
}