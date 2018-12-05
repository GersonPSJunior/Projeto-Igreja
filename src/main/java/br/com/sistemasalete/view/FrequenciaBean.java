package br.com.sistemasalete.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.sistemasalete.model.Frequencia;

/**
 * Backing bean for Frequencia entities.
 * <p/>
 * This class provides CRUD functionality for all Frequencia entities. It
 * focuses purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt>
 * for state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class FrequenciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Frequencia entities
	 */

	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Frequencia frequencia;

	public Frequencia getFrequencia() {
		return this.frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(unitName = "persistence", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		this.conversation.setTimeout(1800000L);
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
			this.conversation.setTimeout(1800000L);
		}

		if (this.id == null) {
			this.frequencia = this.example;
		} else {
			this.frequencia = findById(getId());
		}
	}

	public Frequencia findById(Integer id) {

		return this.entityManager.find(Frequencia.class, id);
	}

	/*
	 * Support updating and deleting Frequencia entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.frequencia);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.frequencia);
				return "view?faces-redirect=true&id="
						+ this.frequencia.getIdFrequencia();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			Frequencia deletableEntity = findById(getId());

			this.entityManager.remove(deletableEntity);
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching Frequencia entities with pagination
	 */

	private int page;
	private long count;
	private List<Frequencia> pageItems;

	private Frequencia example = new Frequencia();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Frequencia getExample() {
		return this.example;
	}

	public void setExample(Frequencia example) {
		this.example = example;
	}

	public String search() {
		this.page = 0;
		return null;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<Frequencia> root = countCriteria.from(Frequencia.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Frequencia> criteria = builder
				.createQuery(Frequencia.class);
		root = criteria.from(Frequencia.class);
		TypedQuery<Frequencia> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Frequencia> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		int qtdMembros = this.example.getQtdMembros();
		if (qtdMembros != 0) {
			predicatesList
					.add(builder.equal(root.get("qtdMembros"), qtdMembros));
		}
		int qtdVisitas = this.example.getQtdVisitas();
		if (qtdVisitas != 0) {
			predicatesList
					.add(builder.equal(root.get("qtdVisitas"), qtdVisitas));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Frequencia> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Frequencia entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Frequencia> getAll() {

		CriteriaQuery<Frequencia> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Frequencia.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Frequencia.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final FrequenciaBean ejbProxy = this.sessionContext
				.getBusinessObject(FrequenciaBean.class);

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return ejbProxy.findById(Integer.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((Frequencia) value).getIdFrequencia());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Frequencia add = new Frequencia();

	public Frequencia getAdd() {
		return this.add;
	}

	public Frequencia getAdded() {
		Frequencia added = this.add;
		this.add = new Frequencia();
		return added;
	}
}
