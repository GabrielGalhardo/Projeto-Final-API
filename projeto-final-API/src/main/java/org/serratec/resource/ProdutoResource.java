package org.serratec.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.dto.produto.ProdutoCadastroDTO;
import org.serratec.dto.produto.ProdutoSimplificadoDTO;
import org.serratec.exception.ClientException;
import org.serratec.exception.ProdutoException;
import org.serratec.models.Categoria;
import org.serratec.models.Produto;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Api de produtos")
public class ProdutoResource {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@ApiOperation(value = "adicionando produtos")
	@PostMapping("/produto")
	public ResponseEntity<?> postProduto(@Validated @RequestBody ProdutoCadastroDTO dto) {
		
		try {Produto produto = dto.toProduto(categoriaRepository);
			
    	if (produtoRepository.existsByCodigo(produto.getCodigo()))
    		throw new ProdutoException("Já existe um produto com este codigo"); 
    	
			if(produto.getPreco() <= 0 )
				throw new ProdutoException("Produto com preço inválido ou menor que 0");
			
			produtoRepository.save(produto);
			
			return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
		
		} catch (ProdutoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@ApiOperation(value = "pesquisando todos produtos")
	@GetMapping("/produto/todos")
	public ResponseEntity<?> getTodos(){
		List<Produto> todos = produtoRepository.findAll();
		List<ProdutoSimplificadoDTO> dtos = new ArrayList<>();
		
		for (Produto produto : todos) 
			dtos.add(new ProdutoSimplificadoDTO(produto));
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	

	@ApiOperation(value = "pesquisando produto por nome")
	@GetMapping("/produto/{nome}")
	public ResponseEntity<?> getProduto(@PathVariable String nome){
		List<Produto> pesquisa = produtoRepository.findAllByNomeContainingIgnoreCase(nome);
		if (pesquisa.isEmpty()) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse nome" , HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(pesquisa , HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "editar categoria do produto")
	@PutMapping("/produto/categoria-edit/{codigo}")
	public ResponseEntity<?> editCategoria(@PathVariable String codigo, @RequestBody Categoria categoria){
		Optional<Produto> pesquisa = produtoRepository.findByCodigo(codigo);
		if (pesquisa == null) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse codigo" , HttpStatus.NOT_FOUND);
		}else {
			Optional<Categoria> categoriaValidator = categoriaRepository.findByNome(categoria.getNome());
			if(categoriaValidator.isEmpty())
				throw new ClientException("Categoria \"" + categoria.getNome() + "\"não encontrada");
			pesquisa.get().setCategoria(categoria);
			return new ResponseEntity<>("Categoria do produto" + pesquisa + " alterada." , HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "editar nome do produto")
	@PutMapping("/produto/nome-edit/{codigo}")
	public ResponseEntity<?> editNome(@PathVariable String codigo, @RequestBody String nome){
		Optional<Produto> pesquisa = produtoRepository.findByCodigo(codigo);
		if (pesquisa == null) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse codigo" , HttpStatus.NOT_FOUND);
		}else {
			pesquisa.get().setNome(nome);
			return new ResponseEntity<>("Nome do produto" + pesquisa + " alterado." , HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "editar descricao do produto")
	@PutMapping("/produto/descricao-edit/{codigo}")
	public ResponseEntity<?> editDescricao(@PathVariable String codigo, @RequestBody String descricao){
		Optional<Produto> pesquisa = produtoRepository.findByCodigo(codigo);
		if (pesquisa == null) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse codigo" , HttpStatus.NOT_FOUND);
		}else {
			pesquisa.get().setDescricao(descricao);
			return new ResponseEntity<>("Descricao do produto" + pesquisa + " alterado." , HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "editar preco do produto")
	@PutMapping("/produto/preco-edit/{codigo}")
	public ResponseEntity<?> editPreco(@PathVariable String codigo, @RequestBody Double preco){
		Optional<Produto> pesquisa = produtoRepository.findByCodigo(codigo);
		if (pesquisa == null) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse codigo" , HttpStatus.NOT_FOUND);
		}else {
			pesquisa.get().setPreco(preco);
			return new ResponseEntity<>("Preco do produto" + pesquisa + " alterado." , HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "editar quantidade estoque do produto")
	@PutMapping("/produto/quantidade-edit/{codigo}")
	public ResponseEntity<?> editQuantidadeEstoque(@PathVariable String codigo, @RequestBody Integer quantidadeEstoque){
		Optional<Produto> pesquisa = produtoRepository.findByCodigo(codigo);
		if (pesquisa == null) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse codigo" , HttpStatus.NOT_FOUND);
		}else {
			pesquisa.get().setQuantidadeEstoque(quantidadeEstoque);
			return new ResponseEntity<>("Preco do produto" + pesquisa + " alterado." , HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "editar imagem do produto")
	@PutMapping("/produto/imagem-edit/{codigo}")
	public ResponseEntity<?> editQuantidadeEstoque(@PathVariable String codigo, @RequestBody String imagem){
		Optional<Produto> pesquisa = produtoRepository.findByCodigo(codigo);
		if (pesquisa == null) {
			return new ResponseEntity<>("Nenhum produto encontrado com esse codigo" , HttpStatus.NOT_FOUND);
		}else {
			pesquisa.get().setImagem(imagem);
			return new ResponseEntity<>("Imagem do produto" + pesquisa + " alterada." , HttpStatus.OK);
		}
	}
	

	@PutMapping("/produto/desabilitar/{codigo}")
    public void putCategoriaDesabilitar(@PathVariable String codigo) {

        Optional<Produto> opcional = produtoRepository.findByCodigo(codigo);

        if(opcional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto inexistente");

        Produto newProduto = opcional.get();
        newProduto.setAtivado(false);

        produtoRepository.save(newProduto);
        throw new ResponseStatusException(HttpStatus.OK, "Categoria desabilitada");
    }
	
	
	@PutMapping("/produto/habilitar/{codigo}")
    public void putCategoriaHabilitar(@PathVariable String codigo) {

        Optional<Produto> opcional = produtoRepository.findByCodigo(codigo);

        if(opcional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto inexistente!");

        Produto newProduto = opcional.get();
        newProduto.setAtivado(true);

        produtoRepository.save(newProduto);
        throw new ResponseStatusException(HttpStatus.OK, "Categoria desabilitada!");
    }	
	
}
