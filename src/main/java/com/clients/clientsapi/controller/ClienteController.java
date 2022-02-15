package com.clients.clientsapi.controller;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clients.clientsapi.dto.ClienteDTO;
import com.clients.clientsapi.model.Cliente;
import com.clients.clientsapi.service.ClienteService;

// bean spring
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/client")
public class ClienteController {

	// injeção de dependência
	@Autowired
	private ClienteService service;

//	@GetMapping("/clients")
//	public List<Cliente> getClientes() {
//		List<Cliente> clientes = this.service.listClientes();
//		return clientes;
//	}

//	@GetMapping("/client/{id}")
//	public Cliente getCliente(@PathVariable("id") long id) {
//		Cliente cliente = this.service.getClienteById(id);
//		return cliente;
//	}

//	@PostMapping("/newclient")
//	public Cliente addCliente(@RequestBody Cliente cliente) {
//		return this.service.salvarCliente(cliente);
//	}

//	@DeleteMapping("/deleteclient")
//	public void deletarCliente(@RequestBody Cliente cliente) {
//		this.service.deletarCliente(cliente);
//	}

//	@PutMapping("/updateclient")
//	public Cliente atualizarCliente(@RequestBody Cliente cliente) {
//		return this.service.atualizarProduto(cliente);
//	}

	@PostMapping("/newclient")
	public ResponseEntity<Object> addCliente(/* recebe os dados como json */
			@RequestBody @Valid ClienteDTO clienteDTO) {

		// validação
		if (this.service.verificarTelefoneExists(clienteDTO.getTelefone()) == true) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: telefone já cadastrado");
		}

		Cliente cliente = new Cliente();
		// cliente.setNome(clienteDTO.getNome());
		// cliente.setTelefone(clienteDTO.getTelefone());
		// cliente.setEmail(clienteDTO.getEmail());
		// cliente.setDataNascimento(LocalDate.parse(clienteDTO.getDataNascimento(),
		// DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		// cliente.setDataNascimento(clienteDTO.getDataNascimento());

		BeanUtils.copyProperties(clienteDTO, cliente); // mapea os dados da classe DTO para a classe model

		Cliente clienteSalvo = this.service.salvarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

//	@GetMapping("/clients")
//	public ResponseEntity<List<Cliente>> getClientes() {
//		List<Cliente> clientes = this.service.listClientes();
//		return ResponseEntity.status(HttpStatus.OK).body(clientes);
//	}
	
	@GetMapping("/clients")
	public ResponseEntity<Page<Cliente>> getClientes(/*paginação na busca*/
			@PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Cliente> clientes = this.service.listClientes(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}

	@GetMapping("/client/{id}")
	public ResponseEntity<Object> getCliente(@PathVariable("id") long id) {
		Optional<Cliente> optional = this.service.getClienteById(id);
		if (optional.isEmpty() == true) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@GetMapping("/client/nome/{nome}")
	public ResponseEntity<Object> getClientePorNome(@PathVariable("nome") String nome) {
		Optional<Cliente> optional = this.service.getClienteByNome(nome);
		if (optional.isEmpty() == true) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		return ResponseEntity.ok(optional.get());
	}

	@DeleteMapping("/deleteclient")
	public ResponseEntity<String> deletarCliente(@PathParam(value = "id") long id) {
		Optional<Cliente> optional = this.service.getClienteById(id);
		if (optional.isEmpty() == true) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		this.service.deletarCliente(optional.get());
		return ResponseEntity.ok("Cliente deletado com sucesso.");
	}

	@PutMapping("/updateclient/{id}")
	public ResponseEntity<Object> atualizarCliente(@PathVariable(value = "id") long id,
			@RequestBody @Valid ClienteDTO clienteDTO) {

		// validação
		if (this.service.verificarTelefoneExists(clienteDTO.getTelefone()) == true) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: telefone já cadastrado");
		}

		Optional<Cliente> optional = this.service.getClienteById(id);
		if (optional.isEmpty() == true) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
//		Cliente cliente = optional.get(); // recupera cliente retornado do banco de dados
//		cliente.setNome(clienteDTO.getNome());
//		cliente.setTelefone(clienteDTO.getTelefone());
//		cliente.setEmail(clienteDTO.getEmail());
//		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(clienteDTO, cliente); // mapea os dados da classe DTO para a classe model
		cliente.setId(optional.get().getId()); // set o id do cliente que esta salvo no banco de dados
		
		this.service.salvarCliente(cliente);
		
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
}
