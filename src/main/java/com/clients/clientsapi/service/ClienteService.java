package com.clients.clientsapi.service;

//import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.clients.clientsapi.model.Cliente;

public interface ClienteService {

	//public abstract List<Cliente> listClientes();
	public abstract Page<Cliente> listClientes(Pageable pegeable);
	
	// public abstract Cliente getClienteById(long id);
	public abstract Optional<Cliente> getClienteById(long id);
	
	public abstract Optional<Cliente> getClienteByNome(String nome);
	
	public abstract Cliente salvarCliente(Cliente cliente);
	
	public abstract boolean verificarTelefoneExists(String telefone);

	public abstract void deletarCliente(Cliente cliente);
	
	public abstract Cliente atualizarProduto(Cliente produto);

	
}
