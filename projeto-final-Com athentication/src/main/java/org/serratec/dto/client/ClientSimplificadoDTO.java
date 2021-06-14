package org.serratec.dto.client;

import org.serratec.models.Client;

public class ClientSimplificadoDTO {
	
	private String email;
	private String username;
	private String cpf;
	private String nome;
	
	public ClientSimplificadoDTO(Client client) {
		this.email = client.getEmail();
		this.nome = client.getNome();
		this.username = client.getUsername();
		this.cpf = client.getCpf();

	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
