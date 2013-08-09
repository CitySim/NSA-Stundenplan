package storage.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class StundenplanStunde extends DefaultEntity{

    @ManyToOne
    @JoinColumn(name="id", 
                insertable=false, updatable=false, 
                nullable=false)
	private Klasse klasse;

    @ManyToOne
    @JoinColumn(name="id", 
                insertable=false, updatable=false, 
                nullable=false)
	private Tag tag;
    
    @ManyToOne
    @JoinColumn(name="id", 
                insertable=false, updatable=false, 
                nullable=false)
	private Stunde stunde;
    
    @ManyToOne
    @JoinColumn(name="id", 
                insertable=false, updatable=false, 
                nullable=false)
	private Lehrer lehrer;
    
    @ManyToOne
    @JoinColumn(name="id", 
                insertable=false, updatable=false, 
                nullable=false)
	private Unterrichtsfach unterrichtsfach;
    
    @ManyToOne
    @JoinColumn(name="id", 
                insertable=false, updatable=false, 
                nullable=false)
	private Raum raum;
    
    @ManyToOne
    @JoinColumn(name="id", 
                insertable=false, updatable=false, 
                nullable=false)
	private Vertretung vertretung;

}
