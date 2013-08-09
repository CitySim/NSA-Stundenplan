package server.entities;

import java.util.Date;

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
	private Date timeFrom;
	
	@Column(name = "zeitBis")
	private Date timeTo;

	public int getId() {
		return this.id;
	}

	public Date getTimeFrom() {
		return this.timeFrom;
	}

	public void setTimeFrom(final Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeTo() {
		return this.timeTo;
	}

	public void setTimeTo(final Date timeTo) {
		this.timeTo = timeTo;
	}

}
