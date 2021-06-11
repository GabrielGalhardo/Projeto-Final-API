package org.serratec.dto.produto;

import java.time.LocalDateTime;

import org.serratec.models.Categoria;
import org.serratec.models.Produto;

public class ProdutoCadastroDTO {

		
		private String nome;
		private String descricao;
		private Double preco;
		private Integer quantidadeEstoque;
		private LocalDateTime dataCadastro;
		private String imagem; 
		private Categoria categoria;
		
		public Produto toProduto() {
			
			Produto produto = new Produto();
			produto.setNome(this.nome);
			produto.setDescricao(this.descricao);
			produto.setPreco(this.preco);
			produto.setQuantidadeEstoque(this.quantidadeEstoque);
		//	LocalDateTime dataCadastro = LocalDateTime.now();
		//	produto.setDataCadastro(this.dataCadastro);
			produto.setImagem(this.imagem);
		//	produto.setCategoria(this.categoria);
					
			return produto;
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
		public Integer getQuantidadeEstoque() {
			return quantidadeEstoque;
		}
		public void setQuantidadeEstoque(Integer quantidadeEstoque) {
			this.quantidadeEstoque = quantidadeEstoque;
		}
		public LocalDateTime getDataCadastro() {
			return dataCadastro;
		}
		public void setDataCadastro(LocalDateTime dataCadastro) {
			this.dataCadastro = dataCadastro;
		}
		public String getImagem() {
			return imagem;
		}
		public void setImagem(String imagem) {
			this.imagem = imagem;
		}
		public Categoria getCategoria() {
			return categoria;
		}
		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
		
		
	
}
