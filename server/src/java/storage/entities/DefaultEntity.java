package storage.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DefaultEntity {

	@Id
	// @Id indicates that this is a unique primary key
	@GeneratedValue
	// @GeneratedValue indicates that value is automatically generated by the
	// server
	private int id;

	public int getId() {
		return this.id;
	}

}
