package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "klasse")
public class Form implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idKlasse")
	private int id;

	@Column(name = "bezeichnung", length = 10)
	private String description;

	@ManyToOne(targetEntity = Teacher.class)
	private Teacher teacher;

	public final int getId() {
		return this.id;
	}

	public final String getDescription() {
		return this.description;
	}

	public final void setDescription(final String description) {
		this.description = description;
	}

	public final Teacher getTeacher() {
		return this.teacher;
	}

	public final void setTeacher(final Teacher teacher) {
		this.teacher = teacher;
	}
}
