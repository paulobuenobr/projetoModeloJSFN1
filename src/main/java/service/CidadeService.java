package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.Cidade;

// TODO: migrar para CDI, mover para pacote service, renomear para <nome>Service.java

//Escopo de aplica��o do bean CDI
@ApplicationScoped
@SuppressWarnings("unchecked")
public class CidadeService {
	
	// Inje��o CDI do EntityManager fabricado pela cl�sse EntityManagerProducer
	@Inject
	private EntityManager em;
	
	// M�todo que utiliza uma named query da entidade para pesquisar e retornar o obejto
	// atrav�s de seu nome
	// Anota��o necess�ria para utilizar uma inje��o de EntityManager via @Produces
	// Transforma o m�todo de persist�ncia em controlado de forma transacional
	@Transactional
	public Cidade pesquisarPorNome(String nome) {
		Query q = em.createNamedQuery("Cidade.findByName");
		q.setParameter("nome", nome);
		return (Cidade)q.getSingleResult();
	}
	
	// M�todo que utiliza uma named query da entidade para pesquisar e retornar todos
	// os objetos persistidos
	// Anota��o necess�ria para utilizar uma inje��o de EntityManager via @Produces
	// Transforma o m�todo de persist�ncia em controlado de forma transacional
	@Transactional
	public List<Cidade> listarCidades() {
		Query q = em.createNamedQuery("Cidade.findAll");
		return q.getResultList();
	}

	// M�todo que utiliza uma named query da entidade para pesquisar e retornar o obejto
	// atrav�s de seu nome
	// Anota��o necess�ria para utilizar uma inje��o de EntityManager via @Produces
	// Transforma o m�todo de persist�ncia em controlado de forma transacional
	@Transactional
	public Cidade pesquisarPorId(Integer id) {
		Query q = em.createNamedQuery("Cidade.findById");
		q.setParameter("id", id);
		return (Cidade)q.getSingleResult();
	}
	
	public List<Cidade> listarCidadesPorEstado(Integer idEstado) {
		Query q = em.createNamedQuery("Cidade.findAllByEstadoId");
		q.setParameter("idEstado", idEstado);
		return q.getResultList();
	}
}
