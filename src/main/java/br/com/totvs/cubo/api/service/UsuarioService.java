package br.com.totvs.cubo.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.totvs.cubo.api.model.Contrato;
import br.com.totvs.cubo.api.model.Demanda;
import br.com.totvs.cubo.api.model.Usuario;
import br.com.totvs.cubo.api.model.request.DemandaWSRequest;
import org.apache.log4j.Logger;

import br.com.totvs.summer.core.dto.NamedParams;
import br.com.totvs.summer.core.exception.CoreException;
import br.com.totvs.summer.core.exception.ServiceException;
import br.com.totvs.summer.core.service.GenericService;
import br.com.totvs.cubo.api.model.dataquery.ZeusDataQuery;
import br.com.totvs.cubo.api.model.response.AutorizacaoWSResponse;
import br.com.totvs.cubo.api.model.response.ClienteWSResponse;
import br.com.totvs.cubo.api.model.response.ConfirmacaoWSResponse;
import br.com.totvs.cubo.api.model.response.ContratoWSResponse;
import br.com.totvs.cubo.api.model.response.DemandaWSResponse;
import br.com.totvs.cubo.api.model.response.ListaClienteWSResponse;
import br.com.totvs.cubo.api.model.response.PerfilWSResponse;
import br.com.totvs.cubo.api.model.response.UsuarioWSResponse;

@Named(value = "usuarioService")
@RequestScoped
public class UsuarioService extends GenericService {

	private static final long serialVersionUID = 7260636704036263636L;

	private Logger logger = Logger.getLogger(UsuarioService.class);

	public UsuarioWSResponse retornaUsuarioPorLogin(String login) throws ServiceException {
		try {
			return (UsuarioWSResponse) findSingleResult(ZeusDataQuery.USUARIO_POR_LOGIN,
					new NamedParams("login", login));
		} catch (CoreException ce) {
			logger.info("Usuário [" + login + "] não encontrado na base de dados.");
			return null;
		} catch (Exception e) {
			throw new ServiceException("Erro ao executar consulta na base de dados do Zeus: " + e.getMessage());
		}
	}

	public AutorizacaoWSResponse retornaAutenticacaoUsuario(String login, String senha) throws ServiceException {
		try {

			AutorizacaoWSResponse resp = new AutorizacaoWSResponse(new UsuarioWSResponse(),
					new ArrayList<PerfilWSResponse>());

			Query query = getDAO().getEm().createQuery(ZeusDataQuery.AUTENTICACAO_USUARIO);
			query.setParameter("login", login);
			query.setParameter("senha", senha);

			Usuario user = (Usuario) query.getSingleResult();
			resp.getUser().setId((Long) user.getId());
			resp.getUser().setNome(user.getNome());

			user.getPerfis().forEach(perfil -> {
				resp.getPerfis().add(new PerfilWSResponse(perfil.getNome()));
			});

			return resp;
		} catch (NoResultException ce) {
			logger.info("Usuário [" + login + "] não encontrado na base de dados.");
			return null;
		} catch (Exception e) {
			throw new ServiceException("Erro ao executar consulta na base de dados do Zeus: " + e.getMessage());
		}
	}

	public ListaClienteWSResponse retornaClientes() throws ServiceException {
		try {
			ListaClienteWSResponse resp = new ListaClienteWSResponse(new ArrayList<ClienteWSResponse>());

			Query query = getDAO().getEm().createQuery(ZeusDataQuery.LISTA_CLIENTES);

			System.out.println("-------------------");
			System.out.println("-------------------" + query.getClass().getName().toString());
//			ArrayList<Cliente> cli = (ArrayList<Cliente>) query.getResultList();
//			resp.getCliente().setId((Long) cli.getId());
//			resp.getCliente().setNome(cli.getNome());
//			resp.getCliente().setCnpj(cli.getCnpj());
//			
//			cli.forEach(Client -> {
//				System.out.println("--------------------------------------------------------");
//				System.out.println(Client.getId().toString()+" "+Client.getNome()+" "+Client.getCnpj()+" "+Client.getDataCadastro());
//				System.out.println("--------------------------------------------------------");
//				resp.getClientes().add( new ClienteWSResponse((Long) Client.getId(),Client.getNome(),Client.getCnpj(),Client.getDataCadastro()));
//			});

//			resp.getCliente().setNome("Jh");
//			resp.getCliente().setId(1L);
//			resp.getCliente().setCnpj("123");
//			resp.getCliente().setDataCadastro("2018");
			resp.setClientes((ArrayList<ClienteWSResponse>) query.getResultList());
			return resp;
//		} catch (CoreException ce) {
//			logger.info("Clientes não encontrado na base de dados.");
//			return null;
		} catch (Exception e) {
			throw new ServiceException("Erro ao executar consulta na base de dados do Zeus: " + e.getMessage());
		}
	}

	public List<ContratoWSResponse> retornaContratosUsuario(String id) throws ServiceException {
		try {

			List<ContratoWSResponse> resp = new ArrayList<ContratoWSResponse>();
			
			List<Contrato> contratos = (List<Contrato>)findList(ZeusDataQuery.CONTRATO_POR_CLIENTE,
					new NamedParams("idCli", Long.parseLong(id)));
			
			for(Contrato ctr:contratos) {
				resp.add(new ContratoWSResponse((Long)ctr.getId(), ctr.getNome(), ctr.getDescricao(), ctr.getDataInicio(), 
						ctr.getDataFim(),ctr.getDataCadastro(), ctr.getDataAtualizacao(), ctr.getUsuarioCadastro(), 
						ctr.getUsuarioAtualizacao(), (Long)ctr.getCliente().getId()));
			}

			return resp;
		} catch (NoResultException ce) {
			logger.info("Contratos não encontrados na base de dados.");
			return null;
		} catch (Exception e) {
			throw new ServiceException("Erro ao executar consulta na base de dados do Zeus: " + e.getMessage());
		}
	}

	public List<DemandaWSResponse> retornaDemandaContratos(String id) throws ServiceException {
		try {

			List<DemandaWSResponse> resp = new ArrayList<DemandaWSResponse>();
			
			
			List<Demanda> demanda = (List<Demanda>)findList(ZeusDataQuery.DEMANDA_POR_CONTRATO,
					new NamedParams("idCon", Long.parseLong(id)));
			
			for(Demanda dmd:demanda) {
				
				resp.add(
						new DemandaWSResponse((Long) dmd.getId(), dmd.getDescricao(), dmd.getUsuarioCadastro(), 
						dmd.getDataCadastro(), (Long)dmd.getContrato().getId()));
			
			}


		

			return resp;
		} catch (NoResultException ce) {
			logger.info("Demandas não encontrados na base de dados.");
			return null;
		} catch (Exception e) {
			throw new ServiceException("Erro ao executar consulta na base de dados do Zeus: " + e.getMessage());
		}
	}

	public ConfirmacaoWSResponse addDemanda(DemandaWSRequest demanda) throws ServiceException {
		try {

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
			String now = ft.format(dNow).toString();
			ConfirmacaoWSResponse resp = new ConfirmacaoWSResponse();
			
			Contrato contrato = (Contrato) getDAO().getEm().find(Contrato.class, Long.parseLong(demanda.getIdContrato()));
			
			Demanda dmd = new Demanda();
			
			dmd.setUsuarioCadastro(Long.parseLong(demanda.getIdUsuario()));
			dmd.setCodigo("");
			dmd.setDataCadastro(now);
			dmd.setContrato(contrato);
			dmd.setDescricao(demanda.getDemanda());
			getDAO().getEm().joinTransaction();
			getDAO().getEm().persist(dmd);
			
			return resp;
		} catch (NoResultException ce) {
			logger.info("Demandas não inseridas na base de dados.");
			return null;
		} catch (Exception e) {
			throw new ServiceException("Erro ao executar inserção na base de dados do Zeus: " + e.getMessage());
		}
	}

	public ConfirmacaoWSResponse removeDemanda(String demanda) throws ServiceException {
		try {
			
			ConfirmacaoWSResponse resp = new ConfirmacaoWSResponse();
			
			Demanda dmd = (Demanda) getDAO().getEm().find(Demanda.class, Long.parseLong(demanda));
			
			getDAO().getEm().joinTransaction();
			getDAO().getEm().remove(dmd);
			
			Demanda dmdr = (Demanda) getDAO().getEm().find(Demanda.class, Long.parseLong(demanda));
			
			if(dmdr==null)
				resp.setStatus("ok");

			return resp;
		} catch (NoResultException ce) {
			logger.info("Demandas não inseridas na base de dados.");
			return null;
		} catch (Exception e) {
			throw new ServiceException("Erro ao executar inserção na base de dados do Zeus: " + e.getMessage());
		}
	}
	
	public ConfirmacaoWSResponse atualizaDemanda(String idDemanda, DemandaWSRequest demanda) throws ServiceException {
		try {
			
			ConfirmacaoWSResponse resp = new ConfirmacaoWSResponse();
			
			Demanda dmd = (Demanda) getDAO().getEm().find(Demanda.class, Long.parseLong(idDemanda));
			dmd.setDescricao(demanda.getDemanda());
			
			getDAO().getEm().joinTransaction();
			getDAO().getEm().merge(dmd);
			
			Demanda dmdr = (Demanda) getDAO().getEm().find(Demanda.class, Long.parseLong(idDemanda));
			
			if(dmdr.getDescricao()==demanda.getDemanda())
				resp.setStatus("ok");

			return resp;
		} catch (NoResultException ce) {
			logger.info("Demandas não inseridas na base de dados.");
			return null;
		} catch (Exception e) {
			throw new ServiceException("Erro ao executar inserção na base de dados do Zeus: " + e.getMessage());
		}
	}


}
