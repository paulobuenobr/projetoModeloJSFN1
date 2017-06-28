package service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.Usuario;

//Escopo de aplica��o do bean CDI
@SessionScoped
@SuppressWarnings("unchecked")
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	// Inje��o CDI do EntityManager fabricado pela classe EntityManagerProducer
	@Inject
	private EntityManager em;

	// Verifica se usu�rio existe ou se pode logar
	// TODO Trocar o nome para portugu�s (Refactoring de m�todo: ALT+SHIFT+C)
	public Usuario verificaLogin(String email, String senha) {

		Usuario userFound = null;

		try {
			email = email.toLowerCase().trim();
			Query q = em.createNamedQuery(Usuario.FIND_BY_EMAIL_SENHA);
			q.setParameter("email", email);
			q.setParameter("senha", stringParaMd5(senha));
			List<Usuario> retorno = q.getResultList();

			if (retorno.size() == 1) {
				userFound = (Usuario) retorno.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userFound;
	}

	private String stringParaMd5(String valor) {
		MessageDigest mDigest;
		try {
			// Instanciamos o nosso HASH MD5, poder�amos usar outro como
			// SHA, por exemplo, mas optamos por MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Converter String para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior compara��o se senhas
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO: arrume ou aguente os cascudos
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO: arrume ou aguente os cascudos
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public Usuario inserir(Usuario usuario) {
		usuario.setDataCadastro(new Date());
		usuario.setSenha(stringParaMd5(usuario.getSenha()));
		em.persist(usuario);
		return usuario;
	}
}
