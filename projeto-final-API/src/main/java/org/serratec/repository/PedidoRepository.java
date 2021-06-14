package org.serratec.repository;
import java.util.Optional;

import org.serratec.dto.pedido.PedidoCadastroDTO;
import org.serratec.models.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository <Pedidos, Long> {

	Optional<Pedidos> findByNumeroPedido(String numeroPedido);


 
}
