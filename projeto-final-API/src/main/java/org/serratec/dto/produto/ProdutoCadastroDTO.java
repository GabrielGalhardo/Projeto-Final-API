package org.serratec.dto.produto;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.serratec.exception.ClientException;
import org.serratec.models.Categoria;
import org.serratec.models.Produto;
import org.serratec.repository.CategoriaRepository;




public class ProdutoCadastroDTO {

		
		private String nome;
		private String codigo;
		private String descricao;
		private Double preco;
		private Integer quantidadeEstoque;
		private Categoria categoria;
		private String imagem; 
		private String imgBase64;
		private String pdfBase64;
		private boolean ativado;
		
		public Produto toProduto(CategoriaRepository categoriaRepository) {
			
			Produto produto = new Produto();
			produto.setNome(this.nome);
			produto.getCodigo(this.codigo);
			produto.setDescricao(this.descricao);
			produto.setPreco(this.preco);
			produto.setQuantidadeEstoque(this.quantidadeEstoque);
			produto.setDataCadastro(LocalDateTime.now());
			produto.setImagem(this.imagem);
			produto.setAtivado(true);
				
			if(pdfBase64 != null) {
				byte[] pdf = Base64.decodeBase64(pdfBase64);
				produto.setPdf(pdf);
			}
			
			
			Optional<Categoria> categoria = categoriaRepository.findByNome(this.categoria.getNome());
			
			if(categoria.isEmpty())
				throw new ClientException("Categoria \"" + this.categoria + "\"não encontrada");
			
			if(categoria.get().getAtivado()  == false)
				throw new ClientException("Categoria \"" + this.categoria + "\"não está ativada");
			
			produto.setCategoria(categoria.get());
			
			
			return produto;
		}

		
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
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
		public String getImgBase64() {
			return imgBase64;
		}
		public void setImgBase64(String imgBase64) {
			this.imgBase64 = imgBase64;
		}
		public String getPdfBase64() {
			return pdfBase64;
		}
		public void setPdfBase64(String pdfBase64) {
			this.pdfBase64 = pdfBase64;
		}
		public boolean isAtivado() {
			return ativado;
		}
		public void setAtivado(boolean ativado) {
			this.ativado = ativado;
		}
		
		
	
}
