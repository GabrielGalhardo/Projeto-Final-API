package org.serratec.resource;

import java.util.List;
import java.util.Optional;

import org.serratec.dto.client.ClientCompletoDTO;
import org.serratec.models.Categoria;
import org.serratec.models.Client;
import org.serratec.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaResource {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@PostMapping("/categoria")
	public ResponseEntity<?> postCategoria(@Validated @RequestBody Categoria categoria) {
		categoriaRepository.save(categoria);
		
		return new ResponseEntity<>("Categoria cadastrada com sucesso!", HttpStatus.OK);
	}

	@GetMapping("/categoria/todos")
	public ResponseEntity<?> getTodos(){
		List<Categoria> categoria = categoriaRepository.findAll();
		if(categoria.isEmpty())
			return new ResponseEntity<>("Nenhuma categoria cadastrada ainda", HttpStatus.NOT_FOUND);	
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

	
	@GetMapping("/categoria/especifico/{nome}")
	public ResponseEntity<?> getDetalhe(@PathVariable String nome) {
		Optional<Categoria> optional = categoriaRepository.findByNome(nome);
		
		if (optional.isEmpty())
			return new ResponseEntity<>("Nome não encontrado", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(optional, HttpStatus.OK);
	}
}
