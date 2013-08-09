package storage.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
	    return "Hello Jersey";
	}
	
}
