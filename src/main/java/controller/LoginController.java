package controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Usuario;
import service.UsuarioService;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	// True se usu�rio est� logado e false caso contr�rio
	private boolean loggedIn;

	// Armazena o usu�rio logado
	private Usuario usuarioLogado;

	// Email e senha digitado pelo usu�rio na p�gina XHTML
	private String email="p@b.com", senha="12345";

	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private MenuController menuController;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Realiza o login caso de tudo certo
	public void doLogin(String pagina) throws Exception {

		// Verifica se o e-mail e senha existem e se o usuario pode logar
		Usuario usuarioFound = (Usuario) usuarioService.verificaLogin(email, senha);

		// Caso n�o tenha retornado nenhum usuario, ent�o mostramos um erro
		// e redirecionamos ele para a p�gina login.xhtml
		// para ele realiza-lo novamente
		if (usuarioFound == null) {
	    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	    			"Usu�rio ou senha inv�lidos!",  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			// caso tenha retornado um usuario, setamos a vari�vel loggedIn
			// como true e guardamos o usuario encontrado na vari�vel
			// usuarioLogado. Depois de tudo, mandamos o usu�rio
			// para a p�gina pessoa.xhtml
			loggedIn = true;
			usuarioLogado = usuarioFound;
			menuController.setPagina(pagina);
			FacesContext.getCurrentInstance()
				.getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml");
		}
	}

	// Realiza o logout do usu�rio logado
	public void doLogout(String pagina) {

		// Setamos a vari�vel usuarioLogado como nulo, ou seja, limpamos
		// os dados do usu�rio que estava logado e depois setamos a vari�vel
		// loggedIn como false para sinalizar que o usu�rio n�o est� mais
		// logado
		usuarioLogado = null;
		loggedIn = false;
		menuController.setPagina(pagina);
		// Mostramos um mensagem ao usu�rio e redirecionamos ele para a p�gina
		// de login
		FacesContext.getCurrentInstance()
		.getApplication().getNavigationHandler()
		.handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml");
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
