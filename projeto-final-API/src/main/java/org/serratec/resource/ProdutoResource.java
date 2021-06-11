package org.serratec.resource;

import java.util.List;

import org.serratec.dto.produto.ProdutoCadastroDTO;
import org.serratec.models.Produto;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoResource {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	
	@PostMapping("/produto")
	public ResponseEntity<?> postProduto(@Validated @RequestBody ProdutoCadastroDTO dto) {
		
		Produto produto = dto.toProduto();
		
		produtoRepository.save(produto);
		
		return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
	}

	
	@GetMapping("/produto/todos")
	public ResponseEntity<?> getTodos(){
		List<Produto> produtos = produtoRepository.findAll();
		if(produtos.isEmpty())
			return new ResponseEntity<>("Nenhum produto cadastrado ainda", HttpStatus.NOT_FOUND);	
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
}
