package org.serratec.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;

import org.serratec.exception.VendaException;
import org.serratec.models.Client;
import org.serratec.models.Pedido;
import org.serratec.models.ItemPedido;


public class PedidoCompletoDTO {

	private String codigo;
	private List<PedidoItensVisualizacaoDTO> produtos;
	private Double valorTotal;
	private LocalDateTime dataPedido;
	private String status;
	private Client client;
	

	public PedidoCompletoDTO(Pedido pedido) throws VendaException {
		
		this.codigo = pedido.getNumeroPedido();		
		List<ItemPedido> produtoPedido = pedido.getItens();
		
		for (ItemPedido produto : produtoPedido) {
			
			PedidoItensVisualizacaoDTO dto = new PedidoItensVisualizacaoDTO(produto.getProduto());
			
			produtos.add(dto);
		}


	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<PedidoItensVisualizacaoDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<PedidoItensVisualizacaoDTO> produtos) {
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
