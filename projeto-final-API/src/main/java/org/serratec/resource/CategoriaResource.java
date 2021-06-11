package org.serratec.resource;

import java.util.List;

import org.serratec.models.Categoria;
import org.serratec.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
	}
	


	@GetMapping("/categoria/todos")
	public ResponseEntity<?> getTodos(){
		List<Categoria> categoria = categoriaRepository.findAll();
		if(categoria.isEmpty())
			return new ResponseEntity<>("Nenhuma categoria cadastrada ainda", HttpStatus.NOT_FOUND);	
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}
}
