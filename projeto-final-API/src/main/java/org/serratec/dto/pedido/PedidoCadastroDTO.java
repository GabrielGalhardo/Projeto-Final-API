package org.serratec.dto.pedido;
import java.time.LocalDateTime;
import java.util.List;
import org.serratec.models.Client;
import org.serratec.models.Pedido;
import org.serratec.models.ItemPedido;

public class PedidoCadastroDTO {

    private String numeroPedido;
    private List<ItemPedido> itens;
    private Double valorTotal;
    private LocalDateTime dataDoPedido;
    private boolean Status;
    private Client clientes;
    

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        
        double valor = 0;
        for(ItemPedido item : itens) {
        	valor +=  item.getPreco() * item.getQuantidade();
        }
        
        pedido.setValorTotal(valor);
        pedido.setNumeroPedido(this.numeroPedido);
        pedido.setItens(this.itens);        	
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