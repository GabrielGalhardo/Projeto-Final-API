package org.serratec.dto.venda;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.serratec.dto.client.ClientSimplificadoDTO;
import org.serratec.models.Produto;


public class VendaDetalhesDTO {

	private String codigoVenda;
	private ClientSimplificadoDTO leitor;
	private LocalDateTime dataPedido;
	private Double total;

	private List<Produto> itens = new ArrayList<>();

	private static DecimalFormat DF = new DecimalFormat("#.##");

	public PedidoDTO(Pedido pedido) {
		this.codigoVenda = pedido.getProtocolo();
		this.leitor = new ClientSimplificadoDTO(pedido.getClient());
		this.dataPedido = pedido.getDataPedido();

		this.itens = pedido.getItens();
		this.total = pedido.getTotal();

		
	}

	public String getCodigoVenda() {
		return codigoVenda;
	}

	

	public Double getTotalSemDesconto() {
		return Double.valueOf(DF.format(this.total));
	}



}
