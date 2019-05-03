package br.com.totvs.cubo.api.model.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CuboWSRequest implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -4715558478231317865L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("titulo")
	private String titulo;

	@JsonProperty("descricao")
	private String descricao;

	@JsonProperty("consulta")
	private String consulta;

	@JsonProperty("stringConexao")
	private String stringConexao;
	
	public CuboWSRequest() {}

	public CuboWSRequest(Integer id, String titulo, String descricao, String consulta, String stringConexao) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.consulta = consulta;
		this.stringConexao = stringConexao;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getStringConexao() {
		return stringConexao;
	}

	public void setStringConexao(String stringConexao) {
		this.stringConexao = stringConexao;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}