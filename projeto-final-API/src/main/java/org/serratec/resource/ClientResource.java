package org.serratec.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.dto.client.ClientCadastroDTO;
import org.serratec.dto.client.ClientCompletoDTO;
import org.serratec.dto.client.ClientSimplificadoDTO;
import org.serratec.exception.ClientException;
import org.serratec.models.Client;
import org.serratec.repository.ClientRepository;
import org.serratec.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ClientResource {
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	EmailService emailService;
	
	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	
	   private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }

	   public static boolean isValidCpf(String cpf) {
	      if ((cpf==null) || (cpf.length()!=11)) return false;

	      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
	      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
	      return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	   }
	
	@PostMapping("/client")
    public ResponseEntity<?> post(@Validated @RequestBody ClientCadastroDTO dto) {
		
		Client cliente = dto.toClient();
        
		try {
        	if(clientRepository.existsByCpf(cliente.getCpf()) || !isValidCpf(cliente.getCpf()))
        		throw new ClientException("CPF invalido ou ja cadastrado");
        	
        	if(clientRepository.existsByUsername(cliente.getUsername()))
        		throw new ClientException("Username já cadastrado");
        	
        	if(clientRepository.existsByTelefone(cliente.getTelefone()))
        		throw new ClientException("Telefone já cadastrado");
        	
        	if (clientRepository.existsByEmail(cliente.getEmail()))
        		throw new ClientException("Já existe um usuario com este e-mail"); 
        	
        	clientRepository.save(cliente);
        	emailService.enviar("Bem vindo", "Seu cadastro foi armazenado", "Serratec@e-commerceSerratec", cliente.getEmail());
            return new ResponseEntity<>("Cliente cadastrado com sucesso", HttpStatus.OK);
        
		}catch (ClientException e) {

                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
           
        }
    }

	/*
	 * @PostMapping("/client") public ResponseEntity<?>
	 * postClient(@Validated @RequestBody Client novo) { try {
	 * clientRepository.save(novo);
	 * 
	 * return new ResponseEntity<>("Cadastrado com sucesso", HttpStatus.OK); } catch
	 * (IllegalArgumentException e) {
	 * 
	 * return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); } }
	 */
	
	@GetMapping("/client/todos")
	public ResponseEntity<?> getTodos() {
		List<Client> todos = clientRepository.findAll();
		List<ClientSimplificadoDTO> dtos = new ArrayList<>();
		
		for (Client client : todos) 
			dtos.add(new ClientSimplificadoDTO(client));
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	  
	@GetMapping("/client/completo/{cpf}")
	public ResponseEntity<?> getDetalhe(@PathVariable String cpf) {
		Optional<Client> optional = clientRepository.findByCpf(cpf);
		
		if (optional.isEmpty())
			return new ResponseEntity<>("CPF não encontrado", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(new ClientCompletoDTO(optional.get()), HttpStatus.OK);
	}	
	
		
	@PutMapping("/cliente/troca/{cpf}")
    public void putCliente(@PathVariable String cpf, @RequestBody Client cliente) {

        Optional<Client> opcional = clientRepository.findByCpf(cpf);

        if(opcional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto inexistente");

        Client newCliente = opcional.get();
        newCliente.setEmail(cliente.getEmail());
        newCliente.setUsername(cliente.getUsername());
        newCliente.setSenha(cliente.getSenha());
        newCliente.setNome(cliente.getNome());
        newCliente.setTelefone(cliente.getTelefone());
        newCliente.setDataNascimento(cliente.getDataNascimento());
       
        emailService.enviar("Cadastro Alterado!", "Os dados da sua conta foram alterados! Caso não tenha sido você, entre em contato com com o suporte do Grupo Cheiroso: 'grupocheiroso3@gmail.com'  que resolveremos seu problema!", "Serratec@e-commerceSerratec", newCliente.getEmail());
       
        clientRepository.save(newCliente);

    }

//	@DeleteMapping("/client/remove/{cpf}")
//    public void deleteClient(@PathVariable String cpf) {
//        Optional<Client> opcional = clientRepository.findByCpf(cpf);
//        //authenticationService.
//        if (opcional.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF foi digitado errado ou é inexistente");
//
//        clientRepository.deleteByCpf(cpf);
//    }
	
}
