package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.Cidade;

// TODO: migrar para CDI, mover para pacote service, renomear para <nome>Service.java

//Escopo de aplicação do bean CDI
@ApplicationScoped
@SuppressWarnings("unchecked")
public class CidadeService {
	
	// Injeção CDI do EntityManager fabricado pela clásse EntityManagerProducer
	@Inject
	private EntityManager em;
	
	// Método que utiliza uma named query da entidade para pesquisar e retornar o obejto
	// através de seu nome
	// Anotação necessária para utilizar uma injeção de EntityManager via @Produces
	// Transforma o método de persistência em controlado de forma transacional
	@Transactional
	public Cidade pesquisarPorNome(String nome) {
		Query q = em.createNamedQuery("Cidade.findByName");
		q.setParameter("nome", nome);
		return (Cidade)q.getSingleResult();
	}
	
	// Método que utiliza uma named query da entidade para pesquisar e retornar todos
	// os objetos persistidos
	// Anotação necessária para utilizar uma injeção de EntityManager via @Produces
	// Transforma o método de persistência em controlado de forma transacional
	@Transactional
	public List<Cidade> listarCidades() {
		Query q = em.createNamedQuery("Cidade.findAll");
		return q.getResultList();
	}

	// Método que utiliza uma named query da entidade para pesquisar e retornar o obejto
	// através de seu nome
	// Anotação necessária para utilizar uma injeção de EntityManager via @Produces
	// Transforma o método de persistência em controlado de forma transacional
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
