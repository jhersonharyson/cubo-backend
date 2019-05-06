package br.com.totvs.cubo.api;

import br.com.totvs.cubo.api.database.Connect;
import br.com.totvs.cubo.api.model.Cubo;
import br.com.totvs.cubo.api.model.request.CuboWSRequest;
import br.com.totvs.cubo.api.model.response.ConfirmacaoWSResponse;
import br.com.totvs.cubo.api.service.CuboService;
import br.com.totvs.cubo.api.service.UsuarioService;
import br.com.totvs.cubo.api.util.ReadData;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

//implements ILoginService


@Api(value = "API Cubo")
@Path("")
public class CuboWS  {

	public static final String MESSAGE = "Message";
	private Logger logger = Logger.getLogger(CuboWS.class);

	@Inject
	private UsuarioService usuarioService;

    @Inject
    private CuboService cuboService;


	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Insere dados para o cubo", produces = MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ConfirmacaoWSResponse.class, message = ""),
			@ApiResponse(code = 500, message = "Header Message", responseHeaders = @ResponseHeader(name = MESSAGE, description = "A operação não pode ser concluída", response = String.class)) })

	@Path("cubo")
	public Response insereAlteraCubo(CuboWSRequest cubo) {
		try {

			// todo altera cubo

			if(cubo.getTitulo().isEmpty() || cubo.getConsulta().isEmpty() || cubo.getTitulo().isEmpty() || cubo.getDescricao().isEmpty())
				throw new Exception("{\"message\": \"Todos os campos devem ser preenchidos\"}");

			Connect conn = new Connect(cubo.getStringConexao(), cubo.getConsulta());

			conn.executa(conn.getConnectionUrl(), conn.getSql(), cuboService.targetDir());

			if(conn.getResult() == null)
				throw new Exception("{\"message\": \"Erro ao persistir dados na base\"}");

			Cubo c = new Cubo(cubo.getTitulo(), cubo.getDescricao(), cubo.getConsulta(), cubo.getStringConexao(), conn.getTarget(), new Date());

			if(cubo.getId() != null)
				c.setId(cubo.getId());

			cuboService.adicionaAlteraCubo(c);

			return Response.ok("{\"status:\": \"ok\"}").header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT,  OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Accept").build();

		} catch (Exception e) {
			logger.error(e);
			return Response.ok(e.getMessage()).status(403).header(MESSAGE, e.getMessage()).build();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Retorna dados de Cubos", produces = MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ConfirmacaoWSResponse.class, message = ""),
			@ApiResponse(code = 500, message = "Header Message", responseHeaders = @ResponseHeader(name = MESSAGE, description = "A operação não pode ser concluída", response = String.class)) })

	@Path("cubos")
	public Response retornaCubo() {
		try {

			// TODO: retornar cubos da base

			return Response.ok(cuboService.retornaCubos()).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT,  OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Accept").build();

		} catch (Exception e) {

			logger.error(e);
			return Response.ok(e.getMessage()).status(403).header(MESSAGE, e.getMessage()).build();

		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Retorna dados de Cubos", produces = MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ConfirmacaoWSResponse.class, message = ""),
			@ApiResponse(code = 500, message = "Header Message", responseHeaders = @ResponseHeader(name = MESSAGE, description = "A operação não pode ser concluída", response = String.class)) })

	@Path("cubo/{id}")
	public Response retornaCuboData(@PathParam("id") Integer id) {
		try {

			Cubo result = cuboService.getCuboById(id);
			if(result != null){
				String arquivo = ReadData.readFile(result.getArquivo());
				return Response.ok(arquivo).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT,  OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Accept").build();
			}

			return Response.ok("{message: \"Nenhum dado encontrado para o paremetro informado\"}").header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT,  OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Accept").build();
		} catch (Exception e) {
			logger.error(e);
			return Response.ok(e.getMessage()).status(403).header(MESSAGE, e.getMessage()).build();
		}
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Insere dados para o cubo", produces = MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, response = ConfirmacaoWSResponse.class, message = ""),
			@ApiResponse(code = 500, message = "Header Message", responseHeaders = @ResponseHeader(name = MESSAGE, description = "A operação não pode ser concluída", response = String.class)) })

	@Path("cubo/user")
	public Response getToken(CuboWSRequest cubo) {
		try {

			// todo: verifica se usuario existe
			// todo: se exitir retorna token
			// todo: caso contrario retorna não autenticado

			return Response.ok("{\"status:\": \"ok\"}").header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT,  OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Accept").build();

		} catch (Exception e) {
			logger.error(e);
			return Response.ok(e.getMessage()).status(403).header(MESSAGE, e.getMessage()).build();
		}
	}
//
//	@Override
//	public Subject login(String login, String senha, String modulo) throws LoginException {
//		try {
//
//			Subject subject = new Subject();
//			subject.setLogin(login);
//			subject.setModulo(modulo);
//			subject.setSenha(senha);
//
//			Map<String, Object> mapa = new HashMap<>();
//			subject.setCustomValues(mapa);
//
//			String token = TokenBuilder.build(new Gson().toJson(subject));
//
//			return this.postServicoSGA(null, token);
//		} catch (Exception e) {
//			throw new LoginException(e);
//		}
//
//	}
//
//	@Override
//	public Subject loginBySSOMethod(String tokenSSO, String modulo) throws LoginException {
//
//		try {
//
//			Map<String, Object> mapa = new HashMap<>();
//			mapa.put("token", tokenSSO);
//			Subject subject = new Subject();
//			subject.setCustomValues(mapa);
//
//			String token = TokenBuilder.build(new Gson().toJson(subject));
//
//			return this.postServicoSGA(null, token);
//		} catch (Exception e) {
//			throw new LoginException(e);
//		}
//
//	}
//    private Subject postServicoSGA(URI uriServico, String token) throws LoginException {
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        try {
//            HttpPost postRequest = new HttpPost(uriServico);
//
//            postRequest.setHeader(HttpHeaders.AUTHORIZATION, token);
//
//
//            HttpResponse response = httpClient.execute(postRequest);
//
//            if(response.getStatusLine().getStatusCode() != 200) {
//                StringBuilder message = new StringBuilder();
//                Arrays.stream(response.getAllHeaders()).filter(header -> header.getName().equalsIgnoreCase(MESSAGE)).forEach(header -> message.append(header.getValue()));
//
//                if(INTERNAL_SERVER_ERROR.getStatusCode() == response.getStatusLine().getStatusCode()) {
//                    throw new LoginException(message.toString());
//                }
//
//                throw new LoginException(message.toString());
//            }
//
//            String conteudoResponse = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//
//            return new Gson().fromJson(conteudoResponse, Subject.class);
//        } catch (IOException e) {
//            throw new LoginException(e);
//        }
//    }



}