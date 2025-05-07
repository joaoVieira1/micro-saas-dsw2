package com.microsaas.tattoo.model.entity;

public class Prestador {
	
	private int id;
	private String nomeFantasia;
	private String nomeCompleto;
	private String foto;
	private String endereco;
	private String descricao;
	private String cidadePrestador;
	
	public Prestador() {
		super();
	}
	
	public Prestador(String nomeFantasia, String nomeCompleto, String foto, String endereco, String descricao, String cidadePrestador) {
		this();
		setNomeFantasia(nomeFantasia);
		setNomeCompleto(nomeCompleto);
		setFoto(foto);
		setEndereco(endereco);
		setDescricao(descricao);
		setCidadePrestador(cidadePrestador);
	}
	
	public Prestador(int id,String nomeFantasia, String nomeCompleto, String foto, String endereco, String descricao, String cidadePrestador) {
		this(nomeFantasia,nomeCompleto,foto,endereco,descricao,cidadePrestador);
		setId(id);
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCidadePrestador() {
		return cidadePrestador;
	}

	public void setCidadePrestador(String cidadePrestador) {
		this.cidadePrestador = cidadePrestador;
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
	
	public String getCaminhoFoto() {
		String URL_UPLOAD = "C:\\IFSP\\5 Semestre\\DESENVOLVIMENTO WEB 2\\desenvolvimento web 2\\uploads\\";
		return URL_UPLOAD + foto;
	}

	@Override
	public String toString() {
		return "Prestador [id=" + id + ", nomeFantasia=" + nomeFantasia + ", nomeCompleto=" + nomeCompleto + ", foto="
				+ foto + ", endereco=" + endereco + ", descricao=" + descricao + ", cidadePrestador=" + cidadePrestador
				+ "]";
	}
	
	
}
