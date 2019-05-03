package br.com.totvs.cubo.api.model.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AutorizacaoWSResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -7220487865245127318L;
	
	private UsuarioWSResponse user;

	private List<PerfilWSResponse> perfis;

	public AutorizacaoWSResponse(UsuarioWSResponse user, List<PerfilWSResponse> perfis) {
		super();
		this.user = user;
		this.perfis = perfis;
	}

	public UsuarioWSResponse getUser() {
		return user;
	}

	public void setUser(UsuarioWSResponse user) {
		this.user = user;
	}

	public List<PerfilWSResponse> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilWSResponse> perfis) {
		this.perfis = perfis;
	}

}