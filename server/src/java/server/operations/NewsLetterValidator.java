package server.operations;

public class NewsLetterValidator {

	public void addAddressToNewsletter(final String eMailAddress,
			final String schoolClass) {

		// add eMailAddress to schoolclass newsLetterEntryList on DB.

	}

	public void sendConfirmationMail(final String eMailAddress) {
		new EmailJobHelper().createConfirmationMail(eMailAddress);
	}

	public void validateConfirmation() {

	}

}
