package org.serratec.dto.categoria;

import org.serratec.models.Categoria;

public class CategoriaSimplesDTO {

	private String nome;
	
	public CategoriaSimplesDTO(Categoria categoria) {
		this.nome = categoria.getNome();
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
