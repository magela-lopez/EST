package com.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.model.Produto;
import com.opencsv.CSVReader;

public class CarregarProdutos {
	
	private static final String archCSV = System.getProperty("user.dir") + "//Products.csv";

	private List<Produto> produtos = new ArrayList<>();
	private String[] campo = null;

	
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

	public void lerCsv() throws IOException {
		
    	CSVReader csvReader = new CSVReader(new FileReader(archCSV));
    		
    		while ((this.campo = csvReader.readNext()) != null) {
    		/*	for (int i =0; i<this.campo.length; i++) {
    				System.out.println(this.campo[i]); Muestra el archivos csv completo
    			}*/

    		//  Entradas aleatorias de estoque   
    		    int rnd = (int) (Math.random()*20)+1; //Cria randomicamente o numero de entradas pro produto
    		    
    		    this.produtos.add(new Produto (campo[0], campo[1], campo[2], campo[21] , rnd)); // Adiciona os produtos a lista.
    		}
    		
    		csvReader.close();
		
    		//Lendo a lista de produtos
    		Iterator iter = getProdutos().iterator();
			while (iter.hasNext()) {
	  			System.out.println(iter.next().toString());
	  		}
    		
			//Chama o metodo menu para mostrar ao usuario o que é possivel fazer, passa para a classe a lista de produtos. 
			Funcoes func = new Funcoes(getProdutos());
			func.menu();
	}
}