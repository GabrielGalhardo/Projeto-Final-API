package org.serratec.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.dto.client.ClientCompletoDTO;
import org.serratec.dto.produto.ProdutoCadastroDTO;
import org.serratec.dto.produto.ProdutoSimplificadoDTO;
import org.serratec.exception.ClientException;
import org.serratec.models.Categoria;
import org.serratec.models.Client;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoResource {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	
	@PostMapping("/produto")
	public ResponseEntity<?> postProduto(@Validated @RequestBody ProdutoCadastroDTO dto) {
		
		try {
			Produto produto = dto.toProduto(categoriaRepository);
			produtoRepository.save(produto);
			return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
		} catch (ClientException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@GetMapping("/produto/todos")
	public ResponseEntity<?> getTodos(){
		List<Produto> todos = produtoRepository.findAll();
		List<ProdutoSimplificadoDTO> dtos = new ArrayList<>();
		
		for (Produto produto : todos) 
			dtos.add(new ProdutoSimplificadoDTO(produto));
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	

	@GetMapping("/produto/{nome}")
	public ResponseEntity<?> getProduto(@PathVariable String nome){
		List<Produto> pesquisa = produtoRepository.findAllByNomeContainingIgnoreCase(nome);
		if (pesquisa.isEmpty()) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse nome" , HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(pesquisa , HttpStatus.OK);
		}
	}
	
	@PutMapping("/produto/categoria-edit/{codigo}")
	public ResponseEntity<?> editCategoria(@PathVariable String codigo, @RequestBody Categoria categoria){
		Produto pesquisa = produtoRepository.findByCodigo(codigo);
		if (pesquisa == null) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse codigo" , HttpStatus.NOT_FOUND);
		}else {
			Optional<Categoria> categoriaValidator = categoriaRepository.findByNome(categoria.getNome());		
			if(categoriaValidator.isEmpty())
				throw new ClientException("Categoria \"" + categoria.getNome() + "\"não encontrada");
			pesquisa.setCategoria(categoria);
			return new ResponseEntity<>("Categoria do produto" + pesquisa + " alterada." , HttpStatus.OK);
		}
	}
			
	@DeleteMapping("/produto/delete/{codigo}")
	public ResponseEntity<?> deleteProduto(@PathVariable String codigo) {
		Produto pesquisa = produtoRepository.findByCodigo(codigo);
		if (pesquisa == null) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse codigo" , HttpStatus.NOT_FOUND);
		}else {
			produtoRepository.delete(pesquisa);
			return new ResponseEntity<>("Produto " + pesquisa + " deletado." , HttpStatus.OK);
		}
	}
		
	
}
