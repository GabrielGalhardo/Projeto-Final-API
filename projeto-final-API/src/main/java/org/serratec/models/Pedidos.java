package org.serratec.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Pedidos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	private String numeroPedido;
		
	@NotNull
	@NotBlank
	@OneToMany(mappedBy="pedidos")
	private List<Produtos_Pedidos> produtos;
	private Double valorTotal;
	private LocalDateTime dataDoPedido;
	private String status;
		
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Client getCliente() {
		return cliente;
	}
	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
		
}
