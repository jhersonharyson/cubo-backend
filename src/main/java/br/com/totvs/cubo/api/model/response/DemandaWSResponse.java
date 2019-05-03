package br.com.totvs.cubo.api.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class DemandaWSResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1838873556880336890L;

	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	private String descricao;
	
	@JsonInclude(Include.NON_NULL)
	private Long usuarioCadastro;
	
	@JsonInclude(Include.NON_NULL)
	private String dataCadastro;
	
	@JsonInclude(Include.NON_NULL)
	private Long contratoId;
	
	public DemandaWSResponse() {}

	public DemandaWSResponse(Long id, String descricao,
			Long usuarioCadastro, String dataCadastro, Long contratoId) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.usuarioCadastro = usuarioCadastro;
		this.dataCadastro = dataCadastro;
		this.contratoId = contratoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Long usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getContratoId() {
		return contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}

}