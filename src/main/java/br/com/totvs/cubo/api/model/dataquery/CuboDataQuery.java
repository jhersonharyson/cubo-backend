package br.com.totvs.cubo.api.model.dataquery;

public class CuboDataQuery {

	public static final String CUBO_CONFIG = " select c from CuboConfig c";
	public static final String CUBOS = " select c from Cubo c order by c.id desc";
	
}