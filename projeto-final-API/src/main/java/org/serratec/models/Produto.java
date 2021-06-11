package org.serratec.models;



import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nome;
		private String descricao;
		private Double preco;
		private Integer quantidadeEstoque;
		private LocalDateTime dataCadastro;
		private String imagem;

		private byte[] capa;
		
		private byte[] pdf; 
		
		@OneToOne
		private Categoria categoria;
		
		public String getImagem() {
			return imagem;
		}
		public void setImagem(String imagem) {
			this.imagem = imagem;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		public Categoria getCategoria() {
			return categoria;
		}
		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
		public byte[] getCapa() {
			return capa;
		}
		public void setCapa(byte[] capa) {
			this.capa = capa;
		}
		public byte[] getPdf() {
			return pdf;
		}
		public void setPdf(byte[] pdf) {
			this.pdf = pdf;
		}
		
		
}
