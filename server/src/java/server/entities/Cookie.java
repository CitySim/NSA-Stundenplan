package server.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Cookie extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	private String cookie;
	private Date invalidForm;

	public final String getCookie() {
		return this.cookie;
	}

	public final void setCookie(final String cookie) {
		this.cookie = cookie;
	}

	public final Date getInvalidForm() {
		return this.invalidForm;
	}

	public final void setInvalidForm(final Date invalidForm) {
		this.invalidForm = invalidForm;
	}
}