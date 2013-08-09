package server.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;

@Entity
public class Lehrer extends DefaultEntity {

	private String kurzName;
	private String name;
	private String vorname;

	public String getKurzName() {
		return this.kurzName;
	}

	public void setKurzName(final String kurzName) {
		this.kurzName = kurzName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(final String vorname) {
		this.vorname = vorname;
	}

}
