package com.controller;

import java.util.*;

import com.model.Arvore;
import com.model.Estoque;
import com.model.Produto;
import com.repository.FuncoesSistema;

public class Funcoes extends Produto implements FuncoesSistema{
	
	private List<Produto> produtos;
	private Arvore<List<Produto>> arv = new Arvore<List<Produto>>();

	//Construtor
	public Funcoes(List<Produto> produtos, Arvore<List<Produto>> arv) {
		
		this.produtos = produtos;
		this.arv = arv;
	}

	//Getters and Setters

	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Arvore<List<Produto>> getArv() {
		return arv;
	}
	public void setArv(Arvore<List<Produto>> arv) {
		this.arv = arv;
	}

	/*------------------------------------------------------------Métodos-----------------------------------------------------------------*/

	@Override
	public void menu() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner (System.in);
		int op;
		try {
			System.out.println("================ MENÚ ================\n"
					+ "1- Buscar produto por código\n"
					+ "2- Buscar produto por nome\n"
					+ "3- Retirar Produtos por Código.\n"
					+ "4- Buscar produto em árvore binária\n"
					+ "0- Sair");
			op = in.nextInt();
			
			switch (op){
			case 1:
				System.out.println(searchById() + "\n");
				menu();
				break;
			case 2:
				List<Produto> list2 = searchByName();
				if (!list2.isEmpty()) {
					Iterator iter = list2.iterator();
					while (iter.hasNext()) {
			  			System.out.println(iter.next().toString());
			  		}
				}else {
					System.out.println("Produto nao encontrado, tente novamente \n");
				}
				menu();
				break;
			case 3:
				System.out.println(removeById());
				menu();
				break;
			case 4: 
				System.out.println("Digite o id do produto");
				Integer id = in.nextInt();
				System.out.println(this.arv.searchTree(id));
				menu();
				break;
			case 0:
				System.exit(0);
				break;
			default:
				menu();
			}
		}catch (InputMismatchException e) {
			System.err.print("Inválido ! Digite um numero inteiro\n");
			menu();
		}
	}

	@Override
	public String searchById() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner (System.in);
		int id;
		try {
		System.out.println("Digite o id do produto");
		id = in.nextInt();
		
		for (Produto produto : this.produtos) {
			
			if(produto.getId() == id ) 
			{
				return produto.toString();
			}
		}
		return "Produto nao encontrado, tente novamente";
		
		}catch (InputMismatchException e) {
			System.err.print("Só é possivel a entrada de numeros, tente novamente\n");
			menu();
		}
		return null;
	}

	@Override
	public List<Produto> searchByName() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner (System.in);
		List<Produto> prodName = new ArrayList<>();
	
		String nome;
		System.out.println("Digite o nome do produto ");
		nome = in.nextLine();
		
		for (Produto produto : this.produtos) {
			
			if (produto.getNome().contains(nome)) {
				prodName.add(produto);
			}
		}
		return prodName;
	}
	
	@Override
	public String removeById() {
	// TODO Auto-generated method stub
	Scanner in = new Scanner (System.in);
	int id, quant, som = 0;
	try {
		System.out.println("Digite o id do produto"); 
		id = in.nextInt();
		for (Produto produto : this.produtos) { 
			
			if(produto.getId() == id )  {
				for (Estoque st : produto.getStock()) { //Faz a soma de todo o estoque (de todas as datas) do produto 
					som = som + st.getQuantidade();	} 
					
			System.out.println("Digite a quantidade a retirar do produto");  
			quant = in.nextInt();
					
			if(produto.getStock().element().getQuantidade() >= quant){
				produto.getStock().element().setQuantidade(produto.getStock().element().getQuantidade() - quant);
				
				if(produto.getStock().element().getQuantidade() == 0) { // Se a quantidade de estoque chegar a 0 então ele remove esse elemento da fila
					produto.getStock().remove();
					return "Quantidade restante do produto id->" + produto.getId() + " nome: " + produto.getNome() + " é: " + produto.getStock();
				}
				return "Quantidade restante do produto id->" + produto.getId() + " nome: " + produto.getNome() + " é: " + produto.getStock();
						
				}else if (som > quant ) { 
					Estoque elemento = produto.getStock().remove(); 
					quant = quant - elemento.getQuantidade() ; 
						
					while (quant !=0) { 
						if (produto.getStock().element().getQuantidade() >= quant) { 
							produto.getStock().element().setQuantidade(produto.getStock().element().getQuantidade() - quant); 
							break; 
						}else {
							elemento.setQuantidade(produto.getStock().remove().getQuantidade()); 
							quant = quant - elemento.getQuantidade() ; 
						}
						}
						return "Quantidade restante do produto id->" + produto.getId() + " nome: " +  produto.getNome() + " é: " + produto.getStock(); 
				}else {
					System.out.println("Estoque insuficiente, deseja retirar o disponivel? (" + som + " produtos)" + "\n1-Sim | 2-Nao");
					int op = in.nextInt();
					while (op >0) {
						switch (op) {
						case 1 :
							Iterator iter = produto.getStock().iterator();
							while(iter.hasNext()) {	produto.getStock().remove(); }
							return produto.toString() + "\n Quantidade retirada: " + som + " produtos";	
						case 2:
							menu();
							break;
						default:
							System.out.println("Estoque insuficiente, deseja retirar o disponivel? \n1-Sim | 2-Nao");
							op = in.nextInt();
							break;
						}//Fim switch case
					}//Fim while		
				}//Fim if else
			}//Fim if
		}//Fim for
		
	}catch (Exception e ) {
		System.err.print("ERRO ! Tente novamente\n" );
		menu();	
		}
	
		return "Produto nao encontrado, tente novamente";
		
	}//Fim metodo

}
