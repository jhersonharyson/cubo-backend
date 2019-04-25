package br.com.totvs.zeus.api.model.response;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ListaContratoWSResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -7220487865245127318L;
	

	private ArrayList<ContratoWSResponse> contrato;

	public ListaContratoWSResponse(ArrayList<ContratoWSResponse> contrato) {
		super();
		this.contrato = contrato;
	}


	public ArrayList<ContratoWSResponse> getContratos() {
		return contrato;
	}

	public void setContratos(ArrayList<ContratoWSResponse> contrato) {
		this.contrato = contrato;
	}

	
}