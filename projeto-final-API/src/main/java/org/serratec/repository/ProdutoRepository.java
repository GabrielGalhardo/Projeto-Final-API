package org.serratec.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.models.Categoria;
import org.serratec.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findAllByCategoria(Categoria categoria);

<<<<<<< HEAD
	List<Produto> findAllByNomeContainingIgnoreCase(String nome);

	Produto findByCodigo(String codigo);
=======
	Optional<Produto> findByNome(String nome);
>>>>>>> 157b17c9412a5a751a3f285ad9069f05940715ac

}
