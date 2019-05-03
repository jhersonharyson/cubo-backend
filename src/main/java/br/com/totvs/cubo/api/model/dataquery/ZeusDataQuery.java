package br.com.totvs.cubo.api.model.dataquery;

public class ZeusDataQuery {

	public static final String USUARIO_POR_LOGIN = " select new UsuarioWSResponse("
			+ "        usr.login, usr.senha, usr.nome) " + "   from Usuario usr  " + "  where usr.login = :login ";

	public static final String AUTENTICACAO_USUARIO = " select  usr " + "   from Usuario usr"
			+ "   where usr.login = :login and usr.senha = :senha  ";

	public static final String LISTA_CLIENTES = " select cli from Cliente cli  ";
	
	
	public static final String CONTRATO_POR_CLIENTE = " select  ctr  from Contrato ctr where ctr.cliente.id = :idCli ";
	
	public static final String DEMANDA_POR_CONTRATO = " select  dmd  from Demanda dmd where dmd.contrato.id = :idCon ";
	
	public static final String INSERE_DEMANDA  = "insert into demanda( id,  codigo, codigo_cliente,  codigo_contrato, descricao, usuario_cadastro,  data_cadastro,  contrato_id) values (?,  ?, ?,  ?, ?, ?,  ?,  ?)";

	public static final String REMOVE_DEMANDA  = "delete from demanda where id = ? ";
	
}