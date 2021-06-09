package org.serratec.resource;

import java.util.ArrayList;
import java.util.List;

import org.serratec.dto.EnderecoCompletoDTO;
import org.serratec.models.Endereco;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnderecoResource {

	@Autowired
	EnderecoRepository repository;
	
//	@GetMapping("/endereco/todos")
//	public ResponseEntity<?> getTodos() {
//		
//		List<Endereco> todos = repository.findAll();
//		return new ResponseEntity<>(todos, HttpStatus.OK);
//	}
	
	@GetMapping("/endereco/todos")
	public ResponseEntity<?> getTodos() {
		List<Endereco> todos = repository.findAll();
		List<EnderecoCompletoDTO> dtos = new ArrayList<>();
		
		for (Endereco endereco : todos) 
			dtos.add(new EnderecoCompletoDTO(endereco));
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PostMapping("/endereco")
	public ResponseEntity<?> postEndereco(@Validated @RequestBody Endereco endereco) {
		repository.save(endereco);
		
		return new ResponseEntity<>("Endereco cadastrado com sucesso!", HttpStatus.OK);
	}
}
