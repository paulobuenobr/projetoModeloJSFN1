package producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Escopo de aplica��o do bean CDI
@ApplicationScoped
public class EntityManagerProducer {
	
    @PersistenceContext(name="crudPU")
    private EntityManager em;

    // Factory de EntityManager
    @Produces
    // Escopo request CDI: o entity manager ser� poduzido a cada chamada a um m�todo de persist�ncia, sendo destru�do depois
    @RequestScoped
    public EntityManager getEntityManager() {
        return em;
    }

}
