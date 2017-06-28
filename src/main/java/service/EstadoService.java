package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.Estado;

// TODO: migrar para CDI, mover para pacote service, renomear para <nome>Service.java

//Escopo de aplicação do bean CDI
@ApplicationScoped
@SuppressWarnings("unchecked")
public class EstadoService {
	
	// Injeção CDI do EntityManager fabricado pela clásse EntityManagerProducer
	@Inject
	private EntityManager em;
	
	// Método que utiliza uma named query da entidade para pesquisar e retornar o obejto
	// através de seu nome
	// Anotação necessária para utilizar uma injeção de EntityManager via @Produces
	// Transforma o método de persistência em controlado de forma transacional
	@Transactional
	public Estado pesquisarPorNome(String nome) {
		Query q = em.createNamedQuery("Estado.findByName");
		q.setParameter("nome", nome);
		return (Estado)q.getSingleResult();
	}
	
	// Método que utiliza uma named query da entidade para pesquisar e retornar todos
	// os objetos persistidos
	// Anotação necessária para utilizar uma injeção de EntityManager via @Produces
	// Transforma o método de persistência em controlado de forma transacional
	@Transactional
	public List<Estado> listarEstados() {
		Query q = em.createNamedQuery("Estado.findAll");
		return q.getResultList();
	}

	// Método que utiliza uma named query da entidade para pesquisar e retornar o obejto
	// através de seu nome
	// Anotação necessária para utilizar uma injeção de EntityManager via @Produces
	// Transforma o método de persistência em controlado de forma transacional
	@Transactional
	public Estado pesquisarPorId(Integer id) {
		Query q = em.createNamedQuery("Estado.findById");
		q.setParameter("id", id);
		return (Estado)q.getSingleResult();
	}
}
