package org.serratec.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.models.Categoria;
import org.serratec.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findAllByCategoria(Categoria categoria);


	List<Produto> findAllByNomeContainingIgnoreCase(String nome);

	Produto findByCodigo(String codigo);


}
