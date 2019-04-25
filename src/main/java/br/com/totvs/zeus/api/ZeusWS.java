package br.com.totvs.zeus.api;

import br.com.totvs.zeus.api.database.Connect;
import br.com.totvs.zeus.api.model.Cubo;
import br.com.totvs.zeus.api.model.request.CuboWSRequest;
import br.com.totvs.zeus.api.model.response.ConfirmacaoWSResponse;
import br.com.totvs.zeus.api.service.CuboService;
import br.com.totvs.zeus.api.service.UsuarioService;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

//import br.com.totvs.zeus.api.service.UsuarioService;

@Api(value = "API Zeus")
@Path("")
public class ZeusWS {

	public static final String MESSAGE = "Message";
	private Logger logger = Logger.getLogger(ZeusWS.class);

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

			// salvar na base correct
			Cubo c = new Cubo(cubo.getId(), cubo.getTitulo(), cubo.getDescricao(), cubo.getConsulta(), cubo.getStringConexao(), new Date());
            cuboService.adicionaAlteraCubo(c);

            // TODO: Connect in database bridge factory
			Connect conn = new Connect(cubo.getStringConexao(), cubo.getConsulta());

			conn.executa(conn.getConnectionUrl(), conn.getSql(), cuboService.stringConexao());

			if(cubo.getTitulo().isEmpty() || cubo.getConsulta().isEmpty() || cubo.getTitulo().isEmpty() || cubo.getDescricao().isEmpty())
				throw new Exception("{\"message\": \"Todos os campos devem ser preenchidos\"}");

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

	@Path("cubo")
	public Response retornaCubo(Cubo cubo) {
		try {

			// TODO: retornar cubos da base

			if(cubo.getTitulo().isEmpty() || cubo.getConsulta().isEmpty() || cubo.getTitulo().isEmpty() || cubo.getDescricao().isEmpty())
				throw new Exception("{\"message\": \"Todos os campos devem ser preenchidos\"}");

			return Response.ok("{\"status:\": \"ok\"}").header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST, GET, PUT,  OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Accept").build();

		} catch (Exception e) {

			logger.error(e);
			return Response.ok(e.getMessage()).status(403).header(MESSAGE, e.getMessage()).build();

		}
	}
}