package server.entities;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Stunde")
public class Lesson {

	@Id
	@GeneratedValue
	@Column(name = "idStunde")
	private int id;

	@Column(name = "zeitVon")
	private Time timeFrom;

	@Column(name = "zeitBis")
	private Time timeTo;

	public int getId() {
		return this.id;
	}

	public Time getTimeFrom() {
		return this.timeFrom;
	}

	public void setTimeFrom(final Time timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Time getTimeTo() {
		return this.timeTo;
	}

	public void setTimeTo(final Time timeTo) {
		this.timeTo = timeTo;
	}

}
