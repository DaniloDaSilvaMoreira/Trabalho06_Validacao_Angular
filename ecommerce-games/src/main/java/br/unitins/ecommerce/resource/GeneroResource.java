package br.unitins.ecommerce.resource;

import java.util.List;

import org.jboss.logging.Logger;
import io.quarkus.arc.DefaultBean;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.genero.GeneroDTO;
import br.unitins.ecommerce.dto.genero.GeneroResponseDTO;
import br.unitins.ecommerce.service.genero.GeneroService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/generos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeneroResource {

    @Inject
    GeneroService generoService;

    private static final Logger LOG = Logger.getLogger(GeneroResource.class);

    @GET
    public List<GeneroResponseDTO> getAll(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
     
        LOG.infof("Buscando todos os gêneros");
        LOG.debug("ERRO DE DEBUG.");
        return generoService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public GeneroResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.infof("Buscando gêneros por ID. ", id);
        LOG.debug("ERRO DE DEBUG.");
        return generoService.getById(id);
    }

    @POST
    public Response insert(GeneroDTO generoDTO) {
        LOG.infof("Inserindo um gênero: %s", generoDTO.nome());
        
        GeneroResponseDTO genero = generoService.insert(generoDTO);
        LOG.infof("Genero (%d) criado com sucesso.", genero.id());
        return Response.status(Status.CREATED).entity(genero).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, GeneroDTO generoDTO) {
        
        generoService.update(id, generoDTO);
        LOG.infof("Genero (%d) atualizado com sucesso.", id);

        return Response
                .status(Status.NO_CONTENT) // 204
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException, NotFoundException {
        try {
            generoService.delete(id);
            LOG.infof("gênero excluído com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT)
                    .build();
        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar um gênero: parâmetros inválidos.", e);
            throw e;
        }
    }

    @GET
    @Path("/count")
    public long count(){
        return generoService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome){
        return generoService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<GeneroResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return generoService.findByNome(nome, page, pageSize);

    }
}
