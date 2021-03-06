package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Long>{
	
	Optional<Client> findById(Long id);

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);

	boolean existsByUsername(String username);

	boolean existsByTelefone(String telefone);

	Optional<Client> findByCpf(String cpf);

	void deleteByCpf(String cpf);

	Optional<Client> findByEmail(String username);
}
