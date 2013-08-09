package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Raum")
public class Room {

	@Id
	@GeneratedValue
	@Column(name = "idRaum")
	private int id;
	
	@Column(name = "bezeichnung", length = 10)
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

}
