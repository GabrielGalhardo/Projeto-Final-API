package org.serratec.dto.pedido;
import java.time.LocalDateTime;
import java.util.List;
import org.serratec.models.Client;
import org.serratec.models.Pedidos;
import org.serratec.models.Produtos_Pedidos;

public class PedidoCadastroDTO {

    private String numeroPedido;
    private List<Produtos_Pedidos> produtos;
    private Double valorTotal;
    private LocalDateTime dataDoPedido;
    private boolean Status;
    private Client clientes;


    public Pedidos toPedido() {
        Pedidos pedido = new Pedidos();
        pedido.setNumeroPedido(this.numeroPedido);
        pedido.setProdutos(this.produtos);
        pedido.setValorTotal(this.valorTotal);
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
    public List<Produtos_Pedidos> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produtos_Pedidos> produtos) {
        this.produtos = produtos;
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