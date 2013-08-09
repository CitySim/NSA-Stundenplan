package server.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Replacement extends DefaultEntity {

	private Date replacementDate;

	public Date getReplacementDate() {
		return this.replacementDate;
	}

	public void setReplacementDate(final Date replacementDate) {
		this.replacementDate = replacementDate;
	}

}
