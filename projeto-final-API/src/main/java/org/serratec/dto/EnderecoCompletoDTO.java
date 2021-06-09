package org.serratec.dto;

import org.serratec.models.Endereco;
import org.springframework.web.client.RestTemplate;

public class EnderecoCompletoDTO {

    private String completo;

    public EnderecoCompletoDTO(Endereco endereco) {

        String uri = "https://viacep.com.br/ws/" + endereco.getCep() + "/json/";

        RestTemplate rest = new RestTemplate();
        EnderecoViaCepDTO viaCep = rest.getForObject(uri, EnderecoViaCepDTO.class);

        this.completo = endereco.getCep() + ", " + endereco.getNumeroResidencia() + viaCep.getLogradouro() + ", " 
                + viaCep.getBairro() + ", " + viaCep.getCidade() + ", " + viaCep.getUf() + ", " + endereco.getComplemento();
    }

    public String getCompleto() {
        return completo;
    }
    public void setCompleto(String completo) {
        this.completo = completo;
    }


}
