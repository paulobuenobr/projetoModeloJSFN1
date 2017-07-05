package api;

import com.google.gson.Gson;
import entity.Cidade;
import entity.Estado;
import entity.Pessoa;
import service.CidadeService;
import service.EstadoService;
import service.PessoaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pessoas")
public class PessoaResource {

    @Inject
    private PessoaService pessoaService;

    @Inject
    private CidadeService cidadeService;

    @Inject
    private EstadoService estadoService;

    @GET
    public Response listar() {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        GenericEntity<List<Pessoa>> gh = new GenericEntity<List<Pessoa>>(pessoas) {
        };
        return Response.ok(gh).build();
    }

    @Path("/excluir/{id}")
    @DELETE
    public Response excluir(@PathParam("id") Integer id) {

        pessoaService.excluir(id);
        return Response.ok("{}").build();
    }

    @Path("/alterar")
    @PUT
    public Response alterar(String json) {
        Gson gson = new Gson();
        Pessoa pessoa = gson.fromJson(json, Pessoa.class);
        pessoaService.alterar(pessoa);
        return Response.ok(pessoa).build();
    }

    @Path("/incluir")
    @POST
    public Response incluir(String json) {
        Gson gson = new Gson();
        Pessoa pessoa = gson.fromJson(json, Pessoa.class);
        pessoaService.incluir(pessoa);
        return Response.ok(pessoa).build();
    }

    @Path("/busca/{nome}")
    @GET
    public Response buscarPorNome(String nome) {
        List<Pessoa> pessoas = pessoaService.buscarPorNome(nome);
        GenericEntity<List<Pessoa>> gh = new GenericEntity<List<Pessoa>>(pessoas) {
        };
        return Response.ok(gh).build();
    }

    @Path("/{id}")
    @GET
    public Response buscarPorId(@PathParam("id") Integer id) {
        Pessoa p = pessoaService.buscarPorId(id);
        return Response.ok(p).build();
    }

    @Path("/cidades")
    @GET
    public Response listarCidades() {
        List<Cidade> cidades = cidadeService.listarCidades();
        GenericEntity<List<Cidade>> gh = new GenericEntity<List<Cidade>>(cidades) {
        };
        return Response.ok(gh).build();
    }

    @Path("/estados")
    @GET
    public Response listarEstados() {
        List<Estado> estados = estadoService.listarEstados();
        GenericEntity<List<Estado>> gh = new GenericEntity<List<Estado>>(estados) {
        };
        return Response.ok(gh).build();
    }

    @Path("/cidades-estado/{idEstado}")
    @GET
    public Response listarCidadesEstado(@PathParam("idEstado") Integer idEstado) {
        List<Cidade> cidades = cidadeService.listarCidadesPorEstado(idEstado);
        GenericEntity<List<Cidade>> gh = new GenericEntity<List<Cidade>>(cidades) {
        };
        return Response.ok(gh).build();
    }

    @Path("/cidade/{idCidade}")
    @GET
    public Response cidadePorId(@PathParam("idCidade") Integer idCidade) {
        Cidade c = cidadeService.pesquisarPorId(idCidade);
        return Response.ok(c).build();
    }

}
