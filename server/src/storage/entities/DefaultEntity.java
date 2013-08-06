package storage.entities;
import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class DefaultEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}
}