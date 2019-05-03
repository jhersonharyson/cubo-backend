package br.com.totvs.cubo.api.model.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ContratoWSResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1838873556880336890L;

	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	private String numero;

	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	private String descricao;
	
	@JsonInclude(Include.NON_NULL)
	private Date dataInicio;
	
	@JsonInclude(Include.NON_NULL)
	private Date dataFim;
	
	@JsonInclude(Include.NON_NULL)
	private Date dataCadastro;
	
	@JsonInclude(Include.NON_NULL)
	private Date dataAtualizacao;
	
	@JsonInclude(Include.NON_NULL)
	private Long usuarioCadastro;
	
	@JsonInclude(Include.NON_NULL)
	private Long usuarioAtualizacao;
	
	@JsonInclude(Include.NON_NULL)
	private Long clienteId;
		
	public ContratoWSResponse() {}

	public ContratoWSResponse(Long id, String nome, String descricao, Date dataInicio, Date dataFim,
			Date dataCadastro, Date dataAtualizacao, Long usuarioCadastro, Long usuarioAtualizacao, Long clientId) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.dataCadastro = dataCadastro;
		this.dataAtualizacao = dataAtualizacao;
		this.usuarioCadastro = usuarioCadastro;
		this.usuarioAtualizacao = usuarioAtualizacao;
		this.clienteId = clientId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Long getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Long usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Long getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(Long usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

}