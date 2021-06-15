package org.serratec.dto.pedido;

import org.serratec.models.Produto;

public class PedidoItensVisualizacaoDTO {

	private String produto;
	private Integer quantidade;
	private Double preco;
	
	
	public PedidoItensVisualizacaoDTO(Produto produto) {
		this.produto = produto.getNome();
		this.quantidade = produto.getQuantidadeEstoque();
		this.preco = produto.getPreco();
	}


	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
}
