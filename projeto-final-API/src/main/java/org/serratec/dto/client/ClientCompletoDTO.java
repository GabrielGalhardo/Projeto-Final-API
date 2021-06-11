package org.serratec.dto.client;

import java.time.LocalDate;

import org.serratec.dto.endereco.EnderecoCompletoDTO;
import org.serratec.models.Client;

public class ClientCompletoDTO {

	private String email;
	private String username;
	private String senha;
	private String nome;
	private String cpf;
	private String telefone;
	private LocalDate dataNascimento;
	
	private EnderecoCompletoDTO endereco;
	
	public ClientCompletoDTO(Client client) {
		this.email = client.getEmail();
		this.nome = client.getNome();
		this.username = client.getUsername();
		this.cpf = client.getCpf();
		this.telefone = client.getTelefone();
		this.dataNascimento = client.getDataNascimento();
				
	    this.endereco = new EnderecoCompletoDTO(client.getEndereco());
	}

	
	public EnderecoCompletoDTO getEndereco() {
		return endereco;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public void setEndereco(EnderecoCompletoDTO endereco) {
		this.endereco = endereco;
	}
	
	
}
