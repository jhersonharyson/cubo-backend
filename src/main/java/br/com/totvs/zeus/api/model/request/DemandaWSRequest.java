package br.com.totvs.zeus.api.model.request;



import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DemandaWSRequest implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1838873556880336890L;

	@JsonProperty("idUsuario")
	private String idUsuario;

	@JsonProperty("idCliente")
	private String idCliente;
	
	@JsonProperty("idContrato")
	private String idContrato;
	
	@JsonProperty("demanda")
	private String demanda;
	
	public DemandaWSRequest() {}

	public DemandaWSRequest(String idUsuario, String idCliente,String idContrato, String demanda) {
		super();
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.idContrato = idContrato;
		this.demanda = demanda;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getDemanda() {
		return demanda;
	}

	public void setDemanda(String demanda) {
		this.demanda = demanda;
	}

}