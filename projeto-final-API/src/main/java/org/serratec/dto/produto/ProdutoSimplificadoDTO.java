package org.serratec.dto.produto;

import org.serratec.dto.categoria.CategoriaSimplesDTO;
import org.serratec.models.Produto;

public class ProdutoSimplificadoDTO {

	private String nome;
	private Double preco;
	private CategoriaSimplesDTO categoria;

	
	
	public ProdutoSimplificadoDTO(Produto produto) {
		
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.categoria = new CategoriaSimplesDTO(produto.getCategoria());
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public CategoriaSimplesDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaSimplesDTO categoria) {
		this.categoria = categoria;
	}

}
