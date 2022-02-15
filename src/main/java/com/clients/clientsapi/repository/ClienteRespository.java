package com.clients.clientsapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clients.clientsapi.model.Cliente;

// bean spring
@Repository
public interface ClienteRespository extends JpaRepository<Cliente, Long> {
	
	// public abstract Cliente findById(long id);
	public abstract Optional<Cliente> findById(long id);
	
	public abstract Optional<Cliente> findByNome(String nome);
	
	public abstract boolean existsByTelefone(String telefone);
}
