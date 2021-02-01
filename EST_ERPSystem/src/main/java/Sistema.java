
import java.io.IOException;

import com.controller.CarregarProdutos;
import com.controller.Funcoes;

public class Sistema {
	
    public static void main(String[] args) throws IOException {
    	
    	CarregarProdutos produtoCsv = new CarregarProdutos();
    	produtoCsv.lerCsv();

    }
}   	
    