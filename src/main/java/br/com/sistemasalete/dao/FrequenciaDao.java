package br.com.sistemasalete.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.sistemasalete.model.Frequencia;


@Stateless
public class FrequenciaDao {
	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	public void create(Frequencia entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Frequencia entity = em.find(Frequencia.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Frequencia findById(int id) {
		return em.find(Frequencia.class, id);
	}

	public Frequencia update(Frequencia entity) {
		return em.merge(entity);
	}

	public List<Frequencia> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Frequencia> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT f FROM Frequencia f LEFT JOIN FETCH f.culto ORDER BY f.idFrequencia",
						Frequencia.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
