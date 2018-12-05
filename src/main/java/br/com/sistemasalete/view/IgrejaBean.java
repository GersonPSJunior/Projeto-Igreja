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

import br.com.sistemasalete.model.Igreja;

/**
 * Backing bean for Igreja entities.
 * <p/>
 * This class provides CRUD functionality for all Igreja entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class IgrejaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Igreja entities
	 */

	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Igreja igreja;

	public Igreja getIgreja() {
		return this.igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
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
			this.igreja = this.example;
		} else {
			this.igreja = findById(getId());
		}
	}

	public Igreja findById(Integer id) {

		return this.entityManager.find(Igreja.class, id);
	}

	/*
	 * Support updating and deleting Igreja entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.igreja);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.igreja);
				return "view?faces-redirect=true&id="
						+ this.igreja.getIdIgreja();
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
			Igreja deletableEntity = findById(getId());

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
	 * Support searching Igreja entities with pagination
	 */

	private int page;
	private long count;
	private List<Igreja> pageItems;

	private Igreja example = new Igreja();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Igreja getExample() {
		return this.example;
	}

	public void setExample(Igreja example) {
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
		Root<Igreja> root = countCriteria.from(Igreja.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Igreja> criteria = builder.createQuery(Igreja.class);
		root = criteria.from(Igreja.class);
		TypedQuery<Igreja> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Igreja> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String razaoSocial = this.example.getRazaoSocial();
		if (razaoSocial != null && !"".equals(razaoSocial)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("razaoSocial")),
					'%' + razaoSocial.toLowerCase() + '%'));
		}
		int cnpj = this.example.getCnpj();
		if (cnpj != 0) {
			predicatesList.add(builder.equal(root.get("cnpj"), cnpj));
		}
		String endereco = this.example.getEndereco();
		if (endereco != null && !"".equals(endereco)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("endereco")),
					'%' + endereco.toLowerCase() + '%'));
		}
		int numero = this.example.getNumero();
		if (numero != 0) {
			predicatesList.add(builder.equal(root.get("numero"), numero));
		}
		String complemento = this.example.getComplemento();
		if (complemento != null && !"".equals(complemento)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("complemento")),
					'%' + complemento.toLowerCase() + '%'));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Igreja> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Igreja entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Igreja> getAll() {

		CriteriaQuery<Igreja> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Igreja.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Igreja.class))).getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final IgrejaBean ejbProxy = this.sessionContext
				.getBusinessObject(IgrejaBean.class);

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

				return String.valueOf(((Igreja) value).getIdIgreja());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Igreja add = new Igreja();

	public Igreja getAdd() {
		return this.add;
	}

	public Igreja getAdded() {
		Igreja added = this.add;
		this.add = new Igreja();
		return added;
	}
}
