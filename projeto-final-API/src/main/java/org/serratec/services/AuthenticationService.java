package org.serratec.services;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.serratec.models.Client;
import org.serratec.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private ClientRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Client> optional = repository.findByEmail(username);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("User not found");
	}	
	
	public Client getClient() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String corrente = authentication.getName();
		
		return (Client)loadUserByUsername(corrente);
	}

}
