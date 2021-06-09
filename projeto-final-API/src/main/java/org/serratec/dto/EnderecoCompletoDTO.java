package org.serratec.dto;

import org.serratec.models.Endereco;
import org.springframework.web.client.RestTemplate;

public class EnderecoCompletoDTO {

    private String cep;
    private String numeroResidencial;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String cidade;
    private String complemento;

    public EnderecoCompletoDTO(Endereco endereco) {

        String uri = "https://viacep.com.br/ws/" + endereco.getCep() + "/json/";

        RestTemplate rest = new RestTemplate();
        EnderecoViaCepDTO viaCep = rest.getForObject(uri, EnderecoViaCepDTO.class);

        this.cep = endereco.getCep();
        this.numeroResidencial =  endereco.getNumeroResidencia(); 
        this.logradouro =  viaCep.getLogradouro();
        this.bairro = viaCep.getBairro();
        this.cidade = viaCep.getLocalidade();
        this.uf = viaCep.getUf();
        this.complemento = endereco.getComplemento();
    }

    
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumeroResidencial() {
		return numeroResidencial;
	}
	public void setNumeroResidencial(String numeroResidencial) {
		this.numeroResidencial = numeroResidencial;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
