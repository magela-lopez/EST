package com.model;

import java.util.*;

public class Produto {

    private int  id;												
	private float amountMax, amountMin;
	private String nome;
	private Date date = new Date();
	private int quantidade;
	
	protected Queue<Estoque> stock = new LinkedList();
	
	public Produto() {		
	}

	public Produto(String id, String amountMax, String amountMin, String nome , int entradas) {
		this.id = id.contains("Id") ? 0 : Integer.parseInt(id);
		this.amountMax = amountMax.contains("prices.amountMax") ? 0.0f : Float.parseFloat(amountMax);
		this.amountMin = amountMin.contains("prices.amountMin") ? 0.0f : Float.parseFloat(amountMin);
		this.nome = nome;
		addStock(entradas);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public float getAmountMax() {
		return amountMax;
	}
	public void setAmountMax(float amountMax) {
		this.amountMax = amountMax;
	}

	public float getAmountMin() {
		return amountMin;
	}
	public void setAmountMin(float amountMin) {
		this.amountMin = amountMin;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Queue<Estoque> getStock() {
		return stock;
	}

	public void setStock(Queue<Estoque> fila, int entradas) {
		for (int i =0; i<entradas;i++) {
			this.quantidade = (int) (Math.random()*20)+1;
			this.stock.add(new Estoque (this.quantidade, this.date));
		}
	}

	@Override
	public String toString() {
		return "Produto [ID=" + id + ", AMOUNT MAX=" + amountMax + ", AMOUNT MIN=" + amountMin + ", NOME=" + nome
				+"\n ESTOQUE=" + stock + "]";
	}
	
	public Date addDate (int i) {
    	try {
			this.date = new Date();
			Calendar cal = Calendar.getInstance() ; 
	    	cal.set(2021, 00 , 01, 12, 30);
	    	cal.add(Calendar.DATE, i++);
	    	cal.add(Calendar.HOUR, this.date.getHours() + i);
	    	cal.add(Calendar.MINUTE, this.date.getMinutes() + i);
	    	cal.add(Calendar.SECOND, this.date.getSeconds() + i);
	    	this.date = cal.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
		
	}
	
	
	public void addStock(int entradas) {
		for (int i =0; i<entradas;i++) {
			
			if(this.id == 0) {
				break;
			}else {
				this.quantidade = (int) (Math.random()*100)+1;
				this.date = addDate(i);
				this.stock.add(new Estoque (this.quantidade, this.date));
			}
			
		}
	}
}
