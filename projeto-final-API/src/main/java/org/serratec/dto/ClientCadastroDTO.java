package org.serratec.dto;



import org.serratec.models.Client;
import org.serratec.models.Endereco;

public class ClientCadastroDTO {
	
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private String telefone;
	private String dataNascimento;
	private Endereco endereco;
	
	public Client toClient() {
		
		Client cliente = new Client();
		cliente.setNome(this.nome);
		cliente.setEmail(email);
		cliente.setSenha(senha);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);

				
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
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
