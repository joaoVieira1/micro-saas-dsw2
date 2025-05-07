package com.microsaas.tattoo.model.entity;

public class Usuario {
	
	public String email;
	public String senha;
	public TipoUsuario tipoUsuario;
	public int refId;
	
	public Usuario(){
		
	}
	
	public Usuario(String email, String senha, TipoUsuario tipoUsuario, int refId) {
		setEmail(email);
		setSenha(senha);
		setTipoUsuario(tipoUsuario);
		setRefId(refId);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public int getRefId() {
		return refId;
	}
	public void setRefId(int ref_id) {
		this.refId = ref_id;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + ", refId=" + refId
				+ "]";
	}
	
	
	
}
