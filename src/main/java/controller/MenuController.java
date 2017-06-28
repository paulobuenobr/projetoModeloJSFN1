package controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pagina;

	@PostConstruct
	public void init() {
		pagina="apresentacao";
	}
	
	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
