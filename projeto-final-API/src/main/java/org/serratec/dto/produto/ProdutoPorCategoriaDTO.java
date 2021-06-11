package org.serratec.dto.produto;


import org.serratec.models.Produto;

public class ProdutoPorCategoriaDTO {

	private String nome;
	private String descricao;
	private Double preco;
	
	
	public ProdutoPorCategoriaDTO(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
}
