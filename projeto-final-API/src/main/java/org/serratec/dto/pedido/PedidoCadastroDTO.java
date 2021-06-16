package org.serratec.dto.pedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.exception.ClientException;
import org.serratec.models.Client;
import org.serratec.models.ItemPedido;
import org.serratec.models.Pedido;
import org.serratec.models.Produto;
import org.serratec.repository.ItemPedidoRepository;
import org.serratec.repository.PedidoRepository;
import org.serratec.repository.ProdutoRepository;

public class PedidoCadastroDTO {

    private String numeroPedido;
    private List<ItemPedido> itens;
    private Double valorTotal;
    private LocalDateTime dataDoPedido;
    private boolean Status;
    private Client clientes;
    

    public Pedido toPedido(ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository) {
        Pedido pedido = new Pedido();
        

        double valor = 0;
        
        List<ItemPedido> listItemPedido = new ArrayList<>();
        
        for(ItemPedido item : itens) {        	

        	Optional<Produto> optional = produtoRepository.findByCodigo(item.getProduto().getCodigo());
        	
        	
        	if(optional.isEmpty())
        		throw new ClientException("Produto \"" + item.getProduto()+ "\"não encontrado");
        	
        	Produto produto = optional.get();
        	
        	if(produto.getAtivado()  == false)
        		throw new ClientException("Produto \"" + item.getProduto() + "\"não está disponivel");
        	
        	ItemPedido itemPedido = new ItemPedido();
        	
        	itemPedido.setProduto(item.getProduto());
        	itemPedido.setQuantidade(item.getQuantidade());
        	        	
        	itemPedidoRepository.save(itemPedido);
        	
        	listItemPedido.add(itemPedido);
        	
        	valor +=  produto.getPreco() * item.getQuantidade();
        	
       }
        
        
        
        pedido.setValorTotal(valor);
        pedido.setNumeroPedido(pedido.gerarNumeroPedido());
        pedido.setItens(listItemPedido);        	
        pedido.setStatus(false);
        pedido.setCliente(this.clientes);

        return pedido;
    }
    
    
    public String getNumeroPedido() {
        return numeroPedido;
    }
    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    public List<ItemPedido> getItens() {
        return itens;
    }
    public void setProdutos(List<ItemPedido> itens) {
        this.itens = itens;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public LocalDateTime getDataDoPedido() {
        return dataDoPedido;
    }
    public void setDataDoPedido(LocalDateTime dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public Client getClientes() {
        return clientes;
    }
    public void setClientes(Client clientes) {
        this.clientes = clientes;
    }


}