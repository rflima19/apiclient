package com.clients.clientsapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -6688878146881708222L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, length = 20)
	private String nome;

	@Column(nullable = false, length = 13)
	private String telefone;

	@Column(nullable = false, length = 50)
	private String email;
	
	@Column(name="nascimento", nullable = false, length = 10)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	public Cliente() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
