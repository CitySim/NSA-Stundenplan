package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class School extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	private String image;
	@Column(length = 1000)
	private String text;

	public final String getImage() {
		return this.image;
	}

	public final void setImage(final String image) {
		this.image = image;
	}

	public final String getText() {
		return this.text;
	}

	public final void setText(final String text) {
		this.text = text;
	}
}