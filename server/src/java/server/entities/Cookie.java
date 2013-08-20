package server.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Cookie extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	private String cookie;
	private Date invalidForm;

	public String getCookie() {
		return this.cookie;
	}

	public void setCookie(final String cookie) {
		this.cookie = cookie;
	}

	public Date getInvalidForm() {
		return invalidForm;
	}

	public void setInvalidForm(Date invalidForm) {
		this.invalidForm = invalidForm;
	}
	
	
}
