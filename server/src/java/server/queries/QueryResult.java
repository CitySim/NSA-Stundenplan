package server.queries;

import java.util.List;

import javax.persistence.Query;

public class QueryResult {
	
	public Object getSingleResult(Query query){
	    query.setMaxResults(1);
	    List<?> list = query.getResultList();
	    if (list == null || list.size() == 0) {
	        return null;
	    }
	    return list.get(0);
	}
}
