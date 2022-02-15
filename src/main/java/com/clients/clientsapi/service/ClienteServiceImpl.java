package com.clients.clientsapi.service;

//import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.clients.clientsapi.model.Cliente;
import com.clients.clientsapi.repository.ClienteRespository;

// bean spring
@Service
public class ClienteServiceImpl implements ClienteService {

	// injeção de dependência
	@Autowired
	private ClienteRespository repository;

//	@Override
//	public List<Cliente> listClientes() {
//		return this.repository.findAll();
//	}
	
	@Override
	public Page<Cliente> listClientes(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

//	@Override
//	public Cliente getClienteById(long id) {
//		return this.repository.findById(id);
//	}
	
	@Override
	public Optional<Cliente> getClienteById(long id) {
		return this.repository.findById(id);
	}
	
	@Override
	public Optional<Cliente> getClienteByNome(String nome) {
		return this.repository.findByNome(nome);
	}

	@Override
	@Transactional
	public Cliente salvarCliente(Cliente cliente) {
		return this.repository.<Cliente>save(cliente);
	}
	
	@Override
	public boolean verificarTelefoneExists(String telefone) {
		return this.repository.existsByTelefone(telefone);
	}
	
	@Override
	@Transactional
	public void deletarCliente(Cliente cliente) {
		this.repository.delete(cliente);
	}
	
	@Override
	@Transactional
	public Cliente atualizarProduto(Cliente produto) {
		return this.repository.save(produto);
	}
}
