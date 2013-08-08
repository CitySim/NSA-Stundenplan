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
public class Stunde extends DefaultEntity {

	private Date zeitVon;
	private Date zeitBis;

	public Date getZeitVon() {
		return this.zeitVon;
	}

	public void setZeitVon(final Date zeitVon) {
		this.zeitVon = zeitVon;
	}

	public Date getZeitBis() {
		return this.zeitBis;
	}

	public void setZeitBis(final Date zeitBis) {
		this.zeitBis = zeitBis;
	}

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="id")
    @IndexColumn(name="indx")
    private List<StundenplanStunde> stundenPlanStunde;

}
