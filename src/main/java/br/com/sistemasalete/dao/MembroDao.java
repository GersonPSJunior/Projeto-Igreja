package br.com.sistemasalete.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.sistemasalete.model.Membro;


@Stateless
public class MembroDao {
	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	public void create(Membro entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Membro entity = em.find(Membro.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Membro findById(int id) {
		return em.find(Membro.class, id);
	}

	public Membro update(Membro entity) {
		return em.merge(entity);
	}

	public List<Membro> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Membro> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT m FROM Membro m LEFT JOIN FETCH m.cargo LEFT JOIN FETCH m.igreja LEFT JOIN FETCH m.municipio ORDER BY m.idMembro",
						Membro.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
