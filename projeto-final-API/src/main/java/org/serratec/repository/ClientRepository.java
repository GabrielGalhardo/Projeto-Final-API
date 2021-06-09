package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Long>{
	Optional<Client> findByCpf(Long id);

	boolean existsByEmail(String email);
}
