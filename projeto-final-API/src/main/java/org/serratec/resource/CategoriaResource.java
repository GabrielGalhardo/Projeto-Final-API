package org.serratec.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.dto.produto.ProdutoPorCategoriaDTO;
import org.serratec.models.Categoria;
import org.serratec.models.Produto;
import org.serratec.repository.CategoriaRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/produtos/categoria/{categoria}")
	public ResponseEntity<?> getProdutoPorCategoria(@PathVariable String categoria){
		
		Optional<Categoria> optional = categoriaRepository.findByNome(categoria);
		
		if(optional.isEmpty())
			return new ResponseEntity<>("Categoria vazia", HttpStatus.BAD_REQUEST); 
		
		List<Produto> produtos = produtoRepository.findAllByCategoria(optional.get());
		
		List<ProdutoPorCategoriaDTO> dtos = new ArrayList<>();
		
		for(Produto produto : produtos) {
			
			dtos.add(new ProdutoPorCategoriaDTO(produto));
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);	
	}
	
	
	@DeleteMapping("/categoria/{nome}")
	public ResponseEntity<?> deleteCategoria(@PathVariable String nome) {

		Optional<Categoria> opcional = categoriaRepository.findByNome(nome);
		
		if(opcional.isEmpty())
			return new ResponseEntity<>( "Categoria inexistente", HttpStatus.NOT_FOUND);
		
		categoriaRepository.deleteByNome(nome);
		return new ResponseEntity<>("Categoria excluida com sucesso!",HttpStatus.OK);
	}
	
}
