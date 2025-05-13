package com.microsaas.tattoo.model.entity;

public class Cliente {
	
	private int id;
	private String nome;
	private String CPF;
	private String endereco;
	private String contato;
	
	public Cliente() {
		
	}
	
	public Cliente(String nome, String CPF, String endereco, String contato) {
		setId(id);
		setNome(nome);
		setCPF(CPF);
		setEndereco(endereco);
		setContato(contato);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	
	
}
