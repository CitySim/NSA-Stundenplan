package storage.entities;

public class EmailAddresse extends DefaultEntity {

	private String eMailAddress;

	public String geteMailAddress() {
		return this.eMailAddress;
	}

	public void seteMailAddress(final String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

}
