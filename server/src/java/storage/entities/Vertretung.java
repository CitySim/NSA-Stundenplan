package storage.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;

@Entity
public class Vertretung extends DefaultEntity {

	private Date datum;

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(final Date datum) {
		this.datum = datum;
	}

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="id")
    @IndexColumn(name="indx")
    private List<StundenplanStunde> stundenPlanStunde;

}
