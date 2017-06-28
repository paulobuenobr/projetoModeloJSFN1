package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.Estado;

// TODO: migrar para CDI, mover para pacote service, renomear para <nome>Service.java

//Escopo de aplica��o do bean CDI
@ApplicationScoped
@SuppressWarnings("unchecked")
public class EstadoService {
	
	// Inje��o CDI do EntityManager fabricado pela cl�sse EntityManagerProducer
	@Inject
	private EntityManager em;
	
	// M�todo que utiliza uma named query da entidade para pesquisar e retornar o obejto
	// atrav�s de seu nome
	// Anota��o necess�ria para utilizar uma inje��o de EntityManager via @Produces
	// Transforma o m�todo de persist�ncia em controlado de forma transacional
	@Transactional
	public Estado pesquisarPorNome(String nome) {
		Query q = em.createNamedQuery("Estado.findByName");
		q.setParameter("nome", nome);
		return (Estado)q.getSingleResult();
	}
	
	// M�todo que utiliza uma named query da entidade para pesquisar e retornar todos
	// os objetos persistidos
	// Anota��o necess�ria para utilizar uma inje��o de EntityManager via @Produces
	// Transforma o m�todo de persist�ncia em controlado de forma transacional
	@Transactional
	public List<Estado> listarEstados() {
		Query q = em.createNamedQuery("Estado.findAll");
		return q.getResultList();
	}

	// M�todo que utiliza uma named query da entidade para pesquisar e retornar o obejto
	// atrav�s de seu nome
	// Anota��o necess�ria para utilizar uma inje��o de EntityManager via @Produces
	// Transforma o m�todo de persist�ncia em controlado de forma transacional
	@Transactional
	public Estado pesquisarPorId(Integer id) {
		Query q = em.createNamedQuery("Estado.findById");
		q.setParameter("id", id);
		return (Estado)q.getSingleResult();
	}
}
