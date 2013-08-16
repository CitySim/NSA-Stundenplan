package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TODO --> Values should not be null
 * @author oleg.scheltow
 *
 */
@Entity
@Table(name = "Newsletter")
public class Newsletter extends DefaultEntity{


	// Klasse
	@ManyToOne(targetEntity=Form.class)
	private Form form;

	@ManyToOne(targetEntity=EmailAddress.class)
	private EmailAddress email;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}
	
	
}
