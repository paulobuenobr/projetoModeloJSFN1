package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import entity.Cidade;
import entity.Estado;
import entity.Pessoa;
import service.CidadeService;
import service.EstadoService;
import service.PessoaService;

// IMPORTANTE:
// Use sempre anotações de uma mesma API (CDI ou JSF)
// Para CDI, utilize import javax.inject.Named e
// javax.faces.view.ViewScoped
// Para JSF, utilize javax.faces.bean.ManagedBean
// e javax.faces.bean.ViewScoped
@Named
@ViewScoped // Para CDIs, use sempre Serializable
public class PessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;

	private List<Cidade> cidades;

	private List<Estado> estados;

	// CDI
	@Inject
	private PessoaService pessoaService;

	@Inject
	private CidadeService cidadeService;

	@Inject
	private EstadoService estadoService;

	private Estado estadoSelecionado;

	private List<Pessoa> pessoas;

	// Carrega ainformações necessárias depois que o controller foi instanciado
	// (post construct)
	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
		// cidades = cidadeService.listarCidades();
		estados = estadoService.listarEstados();
		cidades = new ArrayList<Cidade>();
		listarPessoas();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void incluir() {
		pessoaService.incluir(pessoa);
	}
	
	public void alterar() {
		pessoaService.alterar(pessoa);
	}

	// Action Listener Primefaces: para uso com a tag p:commandButton
	public void incluirPessoa() {
		pessoaService.incluir(pessoa);
		// Lançamento de mensagem do managed bean para a página (atualiza o
		// Growl)
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa incluída com sucesso!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		// Limpa o conteúdo da página após a persistência
		pessoa = new Pessoa();
		estadoSelecionado = null;
		listarPessoas();
	}

	public void listarPessoas() {
		pessoas = pessoaService.listarPessoas();
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public void filtrarCidadesPorEstado() {
		if (estadoSelecionado != null) {
			cidades = cidadeService.listarCidadesPorEstado(estadoSelecionado.getId());
		} else {
			cidades = new ArrayList<>();
		}
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public void editarPessoa(RowEditEvent event) {
		Pessoa pessoa = (Pessoa) event.getObject();
		pessoaService.alterar(pessoa);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa alterada com sucesso!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		listarPessoas();
	}
	
	public void excluir(Pessoa pessoa) {
		pessoaService.excluir(pessoa);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa excluída com sucesso!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		listarPessoas();
	}

}
