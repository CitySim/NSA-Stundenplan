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

	public final Form getForm() {
		return this.form;
	}

	public final void setForm(final Form form) {
		this.form = form;
	}

	public final EmailAddress getEmail() {
		return this.email;
	}

	public final void setEmail(final EmailAddress email) {
		this.email = email;
	}
}