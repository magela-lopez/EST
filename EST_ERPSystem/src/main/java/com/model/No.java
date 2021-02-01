package com.model;

public class No<T> {
	
	private Produto value;
	private No<T> left;
	private No<T> right;
	
	public No(Produto novoProduto) {
		this.value = novoProduto;
		this.left = null;
		this.right = null;
	}

	public Produto getValor() {
		return value;
	}
	public void setValor(Produto valor) {
		this.value = valor;
	}

	public No<T> getLeft() {
		return left;
	}
	public void setLeft(No<T> left) {
		this.left = left;
	}

	public No<T> getRight() {
		return right;
	}
	public void setRight(No<T> right) {
		this.right = right;
	}
	
}