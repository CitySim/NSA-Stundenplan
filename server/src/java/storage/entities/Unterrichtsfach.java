package storage.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;

@Entity
public class Unterrichtsfach extends DefaultEntity {

	private String kurzName;
	private String bezeichnung;

	public String getKurzName() {
		return this.kurzName;
	}

	public void setKurzName(final String kurzName) {
		this.kurzName = kurzName;
	}

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(final String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="id")
    @IndexColumn(name="indx")
    private List<StundenplanStunde> stundenPlanStunde;

}
