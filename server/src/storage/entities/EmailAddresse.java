package storage.entities;

public class EmailAddresse {

	private int eMailAddressId;
	private String eMailAddress;

	public int geteMailAddressId() {
		return this.eMailAddressId;
	}

	public void seteMailAddressId(final int eMailAddressId) {
		this.eMailAddressId = eMailAddressId;
	}

	public String geteMailAddress() {
		return this.eMailAddress;
	}

	public void seteMailAddress(final String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

}
