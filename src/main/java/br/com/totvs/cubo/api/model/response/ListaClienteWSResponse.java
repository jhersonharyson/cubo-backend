package br.com.totvs.cubo.api.model.response;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ListaClienteWSResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -7220487865245127318L;
	

	private ArrayList<ClienteWSResponse> clientes;

	public ListaClienteWSResponse(ArrayList<ClienteWSResponse> clientes) {
		super();
		this.clientes = clientes;
	}


	public ArrayList<ClienteWSResponse> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<ClienteWSResponse> clientes) {
		this.clientes = clientes;
	}

	
}