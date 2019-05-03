package br.com.totvs.cubo.api.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ClienteWSResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1838873556880336890L;

	@JsonInclude(Include.NON_NULL)
	private Long id;

	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	private String cnpj;
	
	@JsonInclude(Include.NON_NULL)
	private String dataCadastro;
	
	public ClienteWSResponse() {}
	
	
	public ClienteWSResponse(Long id, String nome, String cnpj, String dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.dataCadastro = dataCadastro;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}