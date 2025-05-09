package com.microsaas.tattoo.model.entity;

public class ImagemServico {
	private String foto;
	private String descricao;
	
	
	public ImagemServico() {
		super();
	}
	
	public ImagemServico(String foto, String descricao) {
		this();
		setFoto(foto);
		setDescricao(descricao);
	}

	public String getFoto() {
		return foto;
	}
	
	public String getCaminhoFoto() {
		String URL_UPLOAD = "C:\\IFSP\\5 Semestre\\DESENVOLVIMENTO WEB 2\\desenvolvimento web 2\\uploads\\";
		return URL_UPLOAD + foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "ImagemServico [foto=" + foto + ", descricao=" + descricao + "]";
	}
	
	
}
