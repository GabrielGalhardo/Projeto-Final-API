package org.serratec.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	private String numeroPedido;
		
	@NotNull
	@NotBlank
	@OneToMany(mappedBy="pedidos")
	private List<ItemPedido> itens;
	private Double valorTotal;
	private LocalDateTime dataDoPedido;
	private boolean status;
		
	@ManyToOne
	private Client cliente;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
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
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Client getCliente() {
		return cliente;
	}
	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	public String gerarNumeroPedido() {
		if (this.numeroPedido == null || this.numeroPedido.isBlank()) {

			LocalDateTime agora = LocalDateTime.now();
			Random randomico = new Random();
			String codigo = "v";
			codigo += agora.getYear();
			codigo += agora.getMonth();
			codigo += agora.getDayOfMonth();
			codigo += agora.getHour();
			codigo += agora.getMinute();
			codigo += agora.getSecond();

			for (int i = 0; i < 10; i++) {
				codigo += randomico.nextInt(10);
			}
			this.numeroPedido = codigo;	
		}
		return this.numeroPedido;
	}
		
}
