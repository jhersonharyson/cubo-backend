package br.com.totvs.cubo.api.model.response;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ListaDemandaWSResponse implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -7220487865245127318L;
	

	private ArrayList<DemandaWSResponse> demanda;

	public ListaDemandaWSResponse(ArrayList<DemandaWSResponse> demanda) {
		super();
		this.demanda= demanda;
	}


	public ArrayList<DemandaWSResponse> getDemandas() {
		return demanda;
	}

	public void setDemanda(ArrayList<DemandaWSResponse> demanda) {
		this.demanda = demanda;
	}

	
}