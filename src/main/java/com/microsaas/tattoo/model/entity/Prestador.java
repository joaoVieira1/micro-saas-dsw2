package com.microsaas.tattoo.model.entity;

public class Prestador {
	
	private String nomeFantasia;
	private String nomeCompleto;
	private String foto;
	private String endereco;
	private String descricao;
	
	public Prestador() {
		
	}
	
	public Prestador(String nomeFantasia, String nomeCompleto, String foto, String endereco, String descricao) {
		setNomeFantasia(nomeFantasia);
		setNomeCompleto(nomeCompleto);
		setFoto(foto);
		setEndereco(endereco);
		setDescricao(descricao);
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
