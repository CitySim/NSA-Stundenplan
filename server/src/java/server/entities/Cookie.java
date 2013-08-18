package server.entities;

import javax.persistence.Entity;

@Entity
public class Cookie extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	private String cookie;

	public String getCookie() {
		return this.cookie;
	}

	public void setCookie(final String cookie) {
		this.cookie = cookie;
	}
}
