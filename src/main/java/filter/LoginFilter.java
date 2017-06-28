package filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LoginController;

@WebFilter
public class LoginFilter implements Filter {

	@Inject
	LoginController loginController;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Captura o ManagedBean CDI chamado “loginController”
//		LoginController loginController = (LoginController) ((HttpServletRequest) request)
//				.getSession()
//				.getAttribute("loginController");

		// Verifica se nosso ManagedBean ainda não
		// foi instanciado ou caso a
		// variável loggedIn seja false, assim saberemos que
		// o usuário não está logado
		if (!loginController.isLoggedIn()) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			// Redirecionamos o usuário imediatamente
			// para a página de login.xhtml
			((HttpServletResponse) response).sendRedirect(contextPath + "/index.xhtml");
		} else {
			// Caso ele esteja logado, apenas deixamos
			// que o fluxo continue
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
