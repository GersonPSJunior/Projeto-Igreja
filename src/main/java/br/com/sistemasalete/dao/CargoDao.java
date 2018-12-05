package br.com.sistemasalete.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.sistemasalete.model.Cargo;


@Stateless
public class CargoDao {
	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	public void create(Cargo entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Cargo entity = em.find(Cargo.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Cargo findById(int id) {
		return em.find(Cargo.class, id);
	}

	public Cargo update(Cargo entity) {
		return em.merge(entity);
	}

	public List<Cargo> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Cargo> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT c FROM Cargo c LEFT JOIN FETCH c.membro ORDER BY c.idCargo",
						Cargo.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
