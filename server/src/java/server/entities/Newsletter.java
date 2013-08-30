package server.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Newsletter extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Form.class)
	private Form form;

	@ManyToOne(targetEntity = EmailAddress.class)
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