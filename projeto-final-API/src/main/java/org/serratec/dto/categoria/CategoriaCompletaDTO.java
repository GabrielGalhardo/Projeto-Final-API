package org.serratec.dto.categoria;

import org.serratec.models.Categoria;

public class CategoriaCompletaDTO {

	private String nome;
	private String descricao;
	private Boolean ativado;
	
	public CategoriaCompletaDTO(Categoria categoria) {
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
		this.ativado = categoria.getAtivado();
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
	public Boolean getAtivado() {
		return ativado;
	}
	public void setAtivado(Boolean ativado) {
		this.ativado = ativado;
	}
	

}
