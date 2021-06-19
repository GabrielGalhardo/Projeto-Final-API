package org.serratec.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.dto.categoria.CategoriaCompletaDTO;
import org.serratec.models.Categoria;
import org.serratec.repository.CategoriaRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CategoriaResource {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@PostMapping("/categoria")
	public ResponseEntity<?> postCategoria(@Validated @RequestBody Categoria categoria) {
		categoriaRepository.save(categoria);
		
		return new ResponseEntity<>("Categoria cadastrada com sucesso!", HttpStatus.OK);
	}

	@GetMapping("/categoria/todos")
	public ResponseEntity<?> getTodos(){
		List<Categoria> todos = categoriaRepository.findAll();
		List<CategoriaCompletaDTO> dtos = new ArrayList<>();
		
		if(todos.isEmpty())
			return new ResponseEntity<>("Nenhuma categoria cadastrada ainda", HttpStatus.NOT_FOUND);
		
		for (Categoria categoria : todos) 
			dtos.add(new CategoriaCompletaDTO(categoria));
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
			
	

	
	@GetMapping("/categoria/especifico/{nome}")
	public ResponseEntity<?> getDetalhe(@PathVariable String nome) {
		Optional<Categoria> optional = categoriaRepository.findByNome(nome);
		
		if (optional.isEmpty())
			return new ResponseEntity<>("Nome não encontrado", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(optional, HttpStatus.OK);
	}
	

	
	@PutMapping("/categoria/desabilitar/{nome}")
    public void putCategoriaDesabilitar(@PathVariable String nome) {

        Optional<Categoria> opcional = categoriaRepository.findByNome(nome);

        if(opcional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria inexistente");

        Categoria newCategoria = opcional.get();
        newCategoria.setAtivado(false);

        categoriaRepository.save(newCategoria);
        throw new ResponseStatusException(HttpStatus.OK, "Categoria desabilitada");
    }
	
	
	@PutMapping("/categoria/habilitar/{nome}")
    public void putCategoriaHabilitar(@PathVariable String nome) {

        Optional<Categoria> opcional = categoriaRepository.findByNome(nome);

        if(opcional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria inexistente");

        Categoria newCategoria = opcional.get();
        newCategoria.setAtivado(true);

        categoriaRepository.save(newCategoria);
        throw new ResponseStatusException(HttpStatus.OK, "Categoria habilitada");
    }
	
}
