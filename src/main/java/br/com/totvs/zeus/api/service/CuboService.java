package br.com.totvs.zeus.api.service;

import br.com.totvs.summer.core.dto.NamedParams;
import br.com.totvs.summer.core.exception.ServiceException;
import br.com.totvs.summer.core.service.GenericService;
import br.com.totvs.zeus.api.model.*;
import br.com.totvs.zeus.api.model.dataquery.CuboDataQuery;
import br.com.totvs.zeus.api.model.dataquery.ZeusDataQuery;
import br.com.totvs.zeus.api.model.request.DemandaWSRequest;
import br.com.totvs.zeus.api.model.response.*;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "cuboService")
@RequestScoped
public class CuboService extends GenericService {

    private static final long serialVersionUID = 7260636704036263636L;

    private Logger logger = Logger.getLogger(CuboService.class);

    @Transactional
    public Cubo adicionaAlteraCubo(Cubo cubo) throws ServiceException {
        try {
            this.getDAO().getEm().persist(cubo);
            return cubo;
        } catch (Exception e) {
            throw new ServiceException("Erro ao executar consulta na base de dados do Zeus: " + e.getMessage());
        }
    }

    @Transactional
    public String stringConexao() throws ServiceException {
        try {
            Query query = getDAO().getEm().createQuery(CuboDataQuery.CUBO_CONFIG);
            return ((CuboConfig) query.getResultList().get(0)).getSource();
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
            resp.setClientes((ArrayList<ClienteWSResponse>) query.getResultList());
            return resp;
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
