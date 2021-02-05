package com.model;

public class Arvore <T>{
	
	private No<T> root;

	public Arvore() {
		this.root = null;
	}

	public No<T> getRoot() {
		return root;
	}

	public void setRoot(No<T> raiz) {
		this.root = raiz;
	}

	public void insert(Produto valor) {
		No<T> novoElemento = new No<T>(valor);
		
		if (root == null) {
			this.root = novoElemento;
		}else {
			No<T> atual = this.root;
			
			while(true) 
			{
				if(novoElemento.getValor().getId() < atual.getValor().getId()) {
					if(atual.getLeft() != null) {
						atual = atual.getLeft();
					}else {
						atual.setLeft(novoElemento);
						break;
					}
				}else {
					if(atual.getRight() != null) {
						atual = atual.getRight();
					}else {
						atual.setRight(novoElemento);
						break;
					}
				}
			}
		}
	}
	
	//Metodo para mostrar o conteudo da arvore
	public void inOrder(No<T> atual) {
		if(atual != null) {
			inOrder(atual.getLeft());
			System.out.println(atual.getValor());
			inOrder(atual.getRight());
		}
	}
	
	//Funcionalidade 6
	public String searchTree(Integer id) {
	    if (root == null) return "Arvore vazia" ; // Verifica se a a raiz está vazia, retorna uma mensagem
	    
	    No<T> atual = root;  // Comeca a busca pela raiz
	    
	    while (atual.getValor().getId() != id) { // Procura enquanto nao encontrar igualdade entre os id's
	    	
	      if(id < atual.getValor().getId()){ //se o id desejado é menor do atual
	    	  atual = atual.getLeft();	// vai para esquerda
	      }else { 
	    	  atual = atual.getRight(); } // se nao vai para direita
	      
	      if (atual == null){ //Se percorreu toda a arvore e nao encontrou o id, retorna uma mensagem.
	       return "Produto não encontrado"; 	} 
	      
	    } //Fim while
	    
	    return atual.getValor().toString(); // Encontrou o item e mostra o seu valor
	  }
}

