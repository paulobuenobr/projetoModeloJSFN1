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

	// True se usuário está logado e false caso contrário
	private boolean loggedIn;

	// Armazena o usuário logado
	private Usuario usuarioLogado;

	// Email e senha digitado pelo usuário na página XHTML
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

		// Caso não tenha retornado nenhum usuario, então mostramos um erro
		// e redirecionamos ele para a página login.xhtml
		// para ele realiza-lo novamente
		if (usuarioFound == null) {
	    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	    			"Usuário ou senha inválidos!",  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			// caso tenha retornado um usuario, setamos a variável loggedIn
			// como true e guardamos o usuario encontrado na variável
			// usuarioLogado. Depois de tudo, mandamos o usuário
			// para a página pessoa.xhtml
			loggedIn = true;
			usuarioLogado = usuarioFound;
			menuController.setPagina(pagina);
			FacesContext.getCurrentInstance()
				.getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml");
		}
	}

	// Realiza o logout do usuário logado
	public void doLogout(String pagina) {

		// Setamos a variável usuarioLogado como nulo, ou seja, limpamos
		// os dados do usuário que estava logado e depois setamos a variável
		// loggedIn como false para sinalizar que o usuário não está mais
		// logado
		usuarioLogado = null;
		loggedIn = false;
		menuController.setPagina(pagina);
		// Mostramos um mensagem ao usuário e redirecionamos ele para a página
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
