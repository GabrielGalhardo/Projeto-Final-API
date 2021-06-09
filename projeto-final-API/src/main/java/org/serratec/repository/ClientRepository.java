package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Long>{
	Optional<Client> findByCpf(Long id);

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);

	boolean existsByUsername(String username);

	boolean existsByTelefone(String telefone);
}
