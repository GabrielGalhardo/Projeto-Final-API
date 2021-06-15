package org.serratec.resource;

import java.util.List;
import java.util.Optional;

import org.serratec.dto.pedido.PedidoCadastroDTO;
import org.serratec.exception.PedidoException;
import org.serratec.models.ItemPedido;
import org.serratec.models.Pedido;
import org.serratec.repository.CategoriaRepository;
import org.serratec.repository.PedidoRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class PedidoResource {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	
	@ApiOperation(value = "pesquisando todos pedidos")
	@GetMapping("/pedido/todos")
	public ResponseEntity<?> getPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		
		return new ResponseEntity<>(pedidos, HttpStatus.OK);
	}
	
	@ApiOperation(value = "pesquisando um unico pedido")
	@GetMapping("/pedido/{numeroPedido}")
	public ResponseEntity<?> getPedido(@PathVariable String numeroPedido){
		Optional<Pedido> pedido = pedidoRepository.findByNumeroPedido(numeroPedido);
		if (pedido.isEmpty()) {
			throw new PedidoException("Pedido \"" + numeroPedido + "\"não encontrado");
		}
		
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}
	
	@ApiOperation(value = "criar pedido")
	@PostMapping("/pedido")
	public ResponseEntity<?> postPedido(@RequestBody PedidoCadastroDTO dto){
		Pedido pedido = dto.toPedido();
		
		try {
			for(ItemPedido item : pedido.getItens()) {
				if(item.getQuantidade() == 0)
					throw new PedidoException("Ops, o item \"" + item.getProduto().getNome() + "\" está com uma quantidade inválida ou menor que 1");
				}
			pedidoRepository.save(pedido);
			return new ResponseEntity<>("Pedido criado com sucesso", HttpStatus.OK);
		} catch (PedidoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@ApiOperation(value = "finalizarPedido")
	@PutMapping("/pedido/finalizar/{numeroPedido}")
	public ResponseEntity<?> finalizarPedido(@PathVariable String numeroPedido){
		Optional<Pedido> pedidoOptional = pedidoRepository.findByNumeroPedido(numeroPedido);
		if (pedidoOptional.isEmpty()) {
			throw new PedidoException("Pedido nao encontrado");
		}
		
		Pedido pedido = pedidoOptional.get();
		
		pedido.setStatus(true);
		
		return new ResponseEntity<>("Pedido finalizado", HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "adicionar novos itens ao pedido")
	@PutMapping("pedido/adicionar/{numeroPedido}")
	public ResponseEntity<?> adicionarProdutos(@PathVariable String numeroPedido, @RequestBody ItemPedido produto){
		Optional<Pedido> pedidoOpt = pedidoRepository.findByNumeroPedido(numeroPedido);
		if (pedidoOpt.isEmpty()) {
			throw new PedidoException("Pedido nao encontrado");
		}
		
		Pedido pedido = pedidoOpt.get();
		pedido.getItens().add(produto);
		
		return new ResponseEntity<>("Produto adicionado", HttpStatus.OK);
	}
	
	 @ApiOperation(value = "Deletando pedido ")
	    @DeleteMapping("pessoa/delete/{numeroPedido}")
	    public void deletePedido(@PathVariable String numero) {
	    	pedidoRepository.deleteByNumeroPedido(numero);
	    }
	
}