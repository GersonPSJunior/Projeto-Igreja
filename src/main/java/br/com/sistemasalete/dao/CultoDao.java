package br.com.sistemasalete.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.sistemasalete.model.Culto;

@Stateless
public class CultoDao {
	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	public void create(Culto entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Culto entity = em.find(Culto.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Culto findById(int id) {
		return em.find(Culto.class, id);
	}

	public Culto update(Culto entity) {
		return em.merge(entity);
	}

	public List<Culto> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Culto> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT c FROM Culto c LEFT JOIN FETCH c.igreja LEFT JOIN FETCH c.frequencia ORDER BY c.idCulto",
						Culto.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
