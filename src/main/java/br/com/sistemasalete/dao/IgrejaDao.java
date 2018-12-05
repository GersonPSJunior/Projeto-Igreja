package br.com.sistemasalete.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.sistemasalete.model.Igreja;


@Stateless
public class IgrejaDao {
	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	public void create(Igreja entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Igreja entity = em.find(Igreja.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Igreja findById(int id) {
		return em.find(Igreja.class, id);
	}

	public Igreja update(Igreja entity) {
		return em.merge(entity);
	}

	public List<Igreja> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Igreja> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT i FROM Igreja i LEFT JOIN FETCH i.municipio LEFT JOIN FETCH i.membro LEFT JOIN FETCH i.culto ORDER BY i.idIgreja",
						Igreja.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
