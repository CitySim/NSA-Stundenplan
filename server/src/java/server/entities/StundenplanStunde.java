package server.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
@Entity
public class StundenplanStunde extends DefaultEntity{

	
    @ManyToOne
    @JoinColumn(insertable=false, updatable=false, 
                nullable=false)
    @ForeignKey(name="FK_KLASSE")
	private Klasse klasse;
   
    public Klasse getKlasse() {
		return klasse;
	}

	public void setKlasse(Klasse klasse) {
		this.klasse = klasse;
	}

	@ManyToOne
    @JoinColumn(name="tag_id", 
                insertable=false, updatable=false, 
                nullable=false)
	private Tag tag;
    
    public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@ManyToOne
    @JoinColumn(name="stunde_id",  
                insertable=false, updatable=false, 
                nullable=false)
	private Stunde stunde;
    
    public Stunde getStunde() {
		return stunde;
	}

	public void setStunde(Stunde stunde) {
		this.stunde = stunde;
	}

	@ManyToOne
    @JoinColumn(name="lehrer_id",  
                insertable=false, updatable=false, 
                nullable=false)
	private Lehrer lehrer;
    
	
    public Lehrer getLehrer() {
		return lehrer;
	}

	public void setLehrer(Lehrer lehrer) {
		this.lehrer = lehrer;
	}

	@ManyToOne
    @JoinColumn(name="fach_id",  
                insertable=false, updatable=false, 
                nullable=false)
	private Unterrichtsfach unterrichtsfach;
    
	public Unterrichtsfach getUnterrichtsfach() {
		return unterrichtsfach;
	}

	public void setUnterrichtsfach(Unterrichtsfach unterrichtsfach) {
		this.unterrichtsfach = unterrichtsfach;
	}

	@ManyToOne
    @JoinColumn(name="raum_id",  
                insertable=false, updatable=false, 
                nullable=false)
	private Raum raum;
	
    public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}
    
    @ManyToOne
    @JoinColumn(name="vertretung_id",  
                insertable=false, updatable=false, 
                nullable=false)
	private Vertretung vertretung;

	public Vertretung getVertretung() {
		return vertretung;
	}

	public void setVertretung(Vertretung vertretung) {
		this.vertretung = vertretung;
	}
	
}
