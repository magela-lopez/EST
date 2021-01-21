package com.controller;

import java.util.*;

import com.model.Estoque;
import com.model.Produto;
import com.repository.FuncoesSistema;

public class Funcoes extends Produto implements FuncoesSistema{
	
	private List<Produto> produtos;
	
	//Construtor
	public Funcoes(List<Produto> produtos) {
		
		this.produtos = produtos;
	}

	//Getters and Setters

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
					+ "0- Sair");
			op = in.nextInt();
			
			switch (op){
			
			case 1:
				System.out.println(buscarById() + "\n");
				menu();
				break;
				
			case 2:
				List<Produto> list2 = buscarByName();
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
				System.out.println(retirarById());
				menu();
				break;
				
			case 0:
				System.exit(0);
				break;
			default:
				menu();
			}
		}catch (InputMismatchException e) {
			System.err.print("Inválido ! Digite um numero\n");
			menu();
		}
		
		
	}

	@Override
	public String buscarById() {
		
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
	public List<Produto> buscarByName() {
		
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
	public String retirarById() {
		
		// TODO Auto-generated method stub
		Scanner in = new Scanner (System.in);
		int id, quant, som = 0;
		
		try {
			System.out.println("Digite o id do produto"); // Solicita ao usuario o id do produto
			id = in.nextInt();
			for (Produto produto : this.produtos) { //Procura o id
				
				if(produto.getId() == id )  //Verifica se o id existe
					
				{
						for (Estoque st : produto.getStock()) { //Faz a soma de todo o estoque (de todas as datas) do produto 
							som = som + st.getQuantidade();} 
					
					System.out.println("Digite a quantidade a retirar do produto");  //Solicita a quantidade a ser retirada
					quant = in.nextInt();
					
					if(produto.getStock().element().getQuantidade() >= quant)  //Se a qunatidade desejada pelo usuario estiver disponivel na data mais atinga ele retira e diminui o estoque
					{
						produto.getStock().element().setQuantidade(produto.getStock().element().getQuantidade() - quant);
						
						if(produto.getStock().element().getQuantidade() == 0) { // Se a quantidade de estoque chegar a 0 então ele remove esse elemento da fila
							produto.getStock().remove();
							return "Quantidade restante do produto id->" + produto.getId() + " nome: " + produto.getNome() + " é: " + produto.getStock();
						}
							return "Quantidade restante do produto id->" + produto.getId() + " nome: " + produto.getNome() + " é: " + produto.getStock();  //Mostra o produto e o que ficou no estoque 
						
					}else if (som > quant ) { //Se a quantidade  deseja for maior que o disponivel no elemento mas antigo da fila, mas ainda assim existir estoque disponivel pro produto 
																					//ele vai dimuir até que a quantidade desejada seja abastecida
						
						Estoque elemento = produto.getStock().remove(); //Como a quantidade é maior que elemento mais antigo, ele remove ese elemento da fila
						quant = quant - elemento.getQuantidade() ; // Resta o que foi eliminado (ou seja vendido) da quantidade desejada.
						
						while (quant !=0) { //Enquanto a quantidade não for devidamente vendida ele vai diminuir o estoque
							
							if (produto.getStock().element().getQuantidade() >= quant) { //se o proximo elemento da fila tiver a quantidade suficiente para abastecer o desejado 
								produto.getStock().element().setQuantidade(produto.getStock().element().getQuantidade() - quant); //o sistema diminui o que for necesario do segundo elemento mais antigo da fila
								//quant = 0;
								break; //termina o loop aqui.
							}else {
								elemento.setQuantidade(produto.getStock().remove().getQuantidade()); //Caso precise seguir percorrendo a fila, ele remove o segundo elemento
								quant = quant - elemento.getQuantidade() ; //subtrai o valor que foi removido, e continua o loop ate que a quantidade seja 0
							}
						}
							return "Quantidade restante do produto id->" + produto.getId() + " nome: " +  produto.getNome() + " é: " + produto.getStock();  //Retorna as informaçoes do produto e a quantidade que ficou
					
					}else {
								//Caso o estoque nao for suficiente pergunta se quer retirar o que há disponivel 
							System.out.println("Estoque insuficiente, deseja retirar o disponivel? (" + som + " produtos)" + "\n1-Sim | 2-Nao");
							int op = in.nextInt();
							
							while (op >0) {
								switch (op) {
								case 1 :
									Iterator iter = produto.getStock().iterator();
									while(iter.hasNext()) {
										produto.getStock().remove();
									}
									return produto.toString() + "\n Quantidade retirada: " + som + " produtos";
									
								case 2:
									menu();
									break;
								default:
									System.out.println("Estoque insuficiente, deseja retirar o disponivel? \n1-Sim | 2-Nao");
									op = in.nextInt();
									break;
								}
							}
							
						}
					}	
				}
			
		}catch (Exception e ) {
			System.err.print("ERRO ! Tente novamente\n" );
			menu();
		}
		
		return "Produto nao encontrado, tente novamente";
	}

	
	
}
