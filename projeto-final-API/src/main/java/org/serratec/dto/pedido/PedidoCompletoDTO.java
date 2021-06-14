package org.serratec.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;

import org.serratec.exception.VendaException;
import org.serratec.models.Client;
import org.serratec.models.Pedidos;
import org.serratec.models.Produtos_Pedidos;


public class PedidoCompletoDTO {

	private String codigo;
	private List<PedidoItensDTO> produtos;
	private Double valorTotal;
	private LocalDateTime dataPedido;
	private String status;
	private Client client;
	

	public PedidoCompletoDTO(Pedidos pedido) throws VendaException {
		
		this.codigo = pedido.getNumeroPedido();		
		List<Produtos_Pedidos> produtoPedido = pedido.getProdutos();
		
		for (Produtos_Pedidos produto : produtoPedido) {
			
			PedidoItensDTO dto = new PedidoItensDTO(produto.getProduto());
			
			produtos.add(dto);
		}


	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<PedidoItensDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<PedidoItensDTO> produtos) {
		this.produtos = produtos;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}
