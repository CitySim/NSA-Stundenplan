package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Unterichtsfach")
public class Subject {

	@Id
	@GeneratedValue
	@Column(name = "idUnterichtsfach")
	private int id;

	@Column(name = "kurzname", length = 10)
	private String shortName;

	@Column(name = "bezeichnung", length = 45)
	private String description;

	public int getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}

}
