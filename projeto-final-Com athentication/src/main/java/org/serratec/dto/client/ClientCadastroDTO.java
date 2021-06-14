package org.serratec.dto.client;

import java.time.LocalDate;

import org.serratec.models.Client;
import org.serratec.models.Endereco;

public class ClientCadastroDTO {
	
	private String nome;
	private String email;
	private String username;
	private String senha;
	private String cpf;
	private String telefone;
	private LocalDate dataNascimento;
	private Endereco endereco;
	
	public Client toClient() {
		
		Client cliente = new Client();
		cliente.setNome(this.nome);
		cliente.setUsername(this.username);
		cliente.setEmail(this.email);
		cliente.setSenha(this.senha);
		cliente.setCpf(this.cpf);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setTelefone(this.telefone);

		cliente.setEndereco(this.endereco);
				
		return cliente;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
