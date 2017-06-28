package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.Pessoa;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class PessoaService {

    @Inject
    private EntityManager em;

    @Transactional
    public Pessoa incluir(Pessoa pessoa) {
        em.persist(pessoa);
        return pessoa;
    }

    @Transactional
    public List<Pessoa> listarPessoas() {
        Query q = em.createNamedQuery("Pessoa.findAll");
        return q.getResultList();
    }

    @Transactional
    public Pessoa alterar(Pessoa pessoa) {
        return em.merge(pessoa);
    }

    @Transactional
    public void excluir(Pessoa pessoa) {
        em.remove(em.merge(pessoa));
    }

    @Transactional
    public void excluir(Integer id) {
        em.remove(em.find(Pessoa.class, id));
    }

    public List<Pessoa> buscarPorNome(String nome) {
        Query q = em.createNamedQuery("Pessoa.findByName");
        q.setParameter("nome", nome);
        List<Pessoa> pessoas = q.getResultList();
        return pessoas;
    }

    public Pessoa buscarPorId(Integer id) {
        Query q = em.createNamedQuery("Pessoa.findById");
        q.setParameter("id", id);
        List<Pessoa> pessoas = q.getResultList();
        Pessoa pessoa = null;
        if (pessoas.size()>0) {
            pessoa = pessoas.get(0);
        }
        return pessoa;
    }

}
