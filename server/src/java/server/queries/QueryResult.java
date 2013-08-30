package server.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import server.persistence.HibernateUtil;

public class QueryResult {
	protected final EntityManager em;

	public QueryResult() {
		this.em = HibernateUtil.getEntityManager();

	}

	boolean removeFromDB(final Object object) {
		if (object == null) {
			return false;
		} else {
			this.em.getTransaction().begin();
			this.em.remove(object);
			this.em.getTransaction().commit();
			return true;
		}

	}

	Object getSingleResult(final Query query) {
		query.setMaxResults(1);
		final List<?> list = query.getResultList();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
}
