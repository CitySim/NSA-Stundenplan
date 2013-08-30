package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class School extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	private String image;
	@Column(length=1000)
	private String text;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}