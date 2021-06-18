package org.serratec.security;

import org.serratec.repository.ClientRepository;
import org.serratec.security.filter.TokenAuthenticationFilter;
import org.serratec.security.service.TokenService;
import org.serratec.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private ClientRepository repository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
    //Configurations for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuration for authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers(HttpMethod.POST, "/auth").permitAll()
        	.antMatchers(HttpMethod.POST, "/categoria").permitAll()
        	.antMatchers(HttpMethod.GET, "/categoria/todos").permitAll()
        	.antMatchers(HttpMethod.GET, "/categoria/especifico/{nome}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/categoria/desabilitar/{nome}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/categoria/habilitar/{nome}").permitAll()
        	.antMatchers(HttpMethod.GET, "/produto/categoria/{categoria}").permitAll()
        	.antMatchers(HttpMethod.GET, "/pedido/todos").permitAll()
        	.antMatchers(HttpMethod.GET, "/pedido/{numeroPedido}").permitAll()
        	.antMatchers(HttpMethod.POST, "/produto").permitAll()
        	.antMatchers(HttpMethod.POST, "/produto/todos").permitAll()
        	.antMatchers(HttpMethod.PUT, "/produto/categoria-edit/{codigo}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/produto/nome-edit/{codigo}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/produto/descricao-edit/{codigo}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/produto/preco-edit/{codigo}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/produto/quantidade-edit/{codigo}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/produto/imagem-edit/{codigo}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/produto/desabilitar/{codigo}").permitAll()
        	.antMatchers(HttpMethod.PUT, "/produto/habilitar/{codigo}").permitAll()
        	.antMatchers(HttpMethod.DELETE, "pedido/delete/{numeroPedido}").permitAll()        	       	
        	
        	.anyRequest().authenticated()
        	.and().csrf().disable()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and().addFilterBefore(new TokenAuthenticationFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);
    }

    //Configuration for static resources
    @Override
    public void configure(WebSecurity web) throws Exception {
      	web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/");
    }
    
    
}
