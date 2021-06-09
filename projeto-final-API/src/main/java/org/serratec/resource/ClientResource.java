package org.serratec.resource;

import java.util.List;

import org.serratec.dto.ClientCadastroDTO;
import org.serratec.exception.ClientException;
import org.serratec.models.Client;
import org.serratec.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientResource {
	@Autowired
	ClientRepository clientRepository;
	
	//Lembrar de acertar exception que não está funcionando
	@PostMapping("/client")
    public ResponseEntity<?> post(@Validated @RequestBody ClientCadastroDTO dto) {
		
		Client cliente = dto.toClient();
        
		try {
        	clientRepository.save(cliente);

            return new ResponseEntity<>("Cliente cadastrado com sucesso", HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {

            if (clientRepository.existsByEmail(cliente.getEmail()))
                return new ResponseEntity<>("Já existe um usuario com este e-mail", HttpStatus.BAD_REQUEST);
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (ClientException e) {
           
            if(clientRepository.existsByCpf(cliente.getCpf()))
            	return new ResponseEntity<>("CPF ja cadastrado", HttpStatus.BAD_REQUEST);
            
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
		if (todos.isEmpty()) {
			return new ResponseEntity<>("Nenhum cliente encontrado", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(todos, HttpStatus.OK);
		}
	}
	/*
	 * @GetMapping("client/{cpf}") public ResponseEntity<?>
	 * getEspecifico(@PathVariable Long id) { Optional<Client> optional =
	 * repository.findByCpf(id);
	 * 
	 * if (optional.isEmpty()) return new ResponseEntity<>("Não encontrado",
	 * HttpStatus.NOT_FOUND);
	 * 
	 * return new ResponseEntity<>(new ClienteCompletoDTO(optional.get()),
	 * HttpStatus.OK); }
	 */
}
