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
public class Klasse extends DefaultEntity {
		
	private String bezeichung;
	
	public String getBezeichung() {
		return this.bezeichung;
	}

	public void setBezeichung(final String bezeichung) {
		this.bezeichung = bezeichung;
	}
	
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="id")
    @IndexColumn(name="indx")
    private List<StundenplanStunde> stundenplanStunde;

}
