package br.com.sistemasalete.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.sistemasalete.model.Municipio;

@Stateless
public class MunicipioDao {
	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	public void create(Municipio entity) {
		em.persist(entity);
	}

	public void deleteById(int id) {
		Municipio entity = em.find(Municipio.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Municipio findById(int id) {
		return em.find(Municipio.class, id);
	}

	public Municipio update(Municipio entity) {
		return em.merge(entity);
	}

	public List<Municipio> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Municipio> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT m FROM Municipio m LEFT JOIN FETCH m.igreja LEFT JOIN FETCH m.membro ORDER BY m.idMunicipio",
						Municipio.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
