package controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import entity.Usuario;
import service.UsuarioService;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	@Inject
	private UsuarioService usuarioService;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}
	
	private UploadedFile imagem;
	
	private byte[] conteudoImagem;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void incluirUsuario() {
		// *upload* - se o upload for realizado com opção "advanced", haverá um conteúdo em formato byte[]
		if (conteudoImagem!=null) {
			usuario.setImagem(conteudoImagem);
		// *upload* - com opção "simple", verificar se uma imagem foi selecionada, pois não é obrigatória
		} else if (imagem!=null) {
			usuario.setImagem(imagem.getContents());
		}
		usuarioService.inserir(usuario);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Usuário incluído com sucesso!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		usuario = new Usuario();
	}

	public UploadedFile getImagem() {
		return imagem;
	}

	// *upload* - método chamado apenas com fileupload em modo "simple"
	public void setImagem(UploadedFile imagem) {
		this.imagem = imagem;
	}
	
	// *upload* - método chamado apenas com fileupload em modo "advanced"
	// é preciso extrair o conteúdo em formato byte[] aqui, pois se tentar fazer isso no método incluirUsuario(),
	// o arquivo que sofreu upload não estará mais disponível.
	public void carregarImagem(FileUploadEvent event) {
		if (event.getFile()!=null) {
			this.imagem = event.getFile();
			this.conteudoImagem = imagem.getContents();
		}
	}

}
