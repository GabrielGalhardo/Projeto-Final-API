package org.serratec.dto.produto;

import java.time.LocalDateTime;

import org.apache.tomcat.util.codec.binary.Base64;
import org.serratec.models.Categoria;
import org.serratec.models.Produto;



public class ProdutoCadastroDTO {

		
		private String nome;
		private String descricao;
		private Double preco;
		private Integer quantidadeEstoque;
		private String imagem; 
		private Categoria categoria;
		private String imgBase64;
		private String pdfBase64;
		
		public Produto toProduto() {
			
			Produto produto = new Produto();
			produto.setNome(this.nome);
			produto.setDescricao(this.descricao);
			produto.setPreco(this.preco);
			produto.setQuantidadeEstoque(this.quantidadeEstoque);
			produto.setDataCadastro(LocalDateTime.now());
			produto.setImagem(this.imagem);
		//	produto.setCategoria(this.categoria);
				
			if(pdfBase64 != null) {
				byte[] pdf = Base64.decodeBase64(pdfBase64);
				produto.setPdf(pdf);
			}
			
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
