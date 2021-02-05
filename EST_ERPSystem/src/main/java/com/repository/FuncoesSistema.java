package com.repository;

import java.util.List;
import com.model.Produto;

public interface FuncoesSistema {

	public void menu();
	public String searchById();
	public List<Produto> searchByName();
	public String removeById();
	public String binarySearch();
	
}
