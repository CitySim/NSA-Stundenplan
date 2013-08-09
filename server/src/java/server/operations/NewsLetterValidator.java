package server.operations;

/**
 * Class used to add newsletter entries on the database. Generates URLs for
 * confirming the registration / removing of eMailAddresses.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class NewsLetterValidator {

	public void addAddress(final String eMailAddress, final String schoolClass) {

		new EmailJobHelper().sendConfirmationMail(eMailAddress, schoolClass);

	}

	public String generateRegistrationLink(final String schoolClass,
			final String eMailAddress) {

		// createLink containing our webSite URL + add + emailAddress + to
		// schoolClass

		return null;
	}

	public String generateRemoveLink(final String schoolClass,
			final String eMailAddress) {

		// createLink containing our webSite URL + remove + emailAddress + to
		// schoolClass

		return null;
	}

	public void validateConfirmation() {

		// add eMailAddress for schoolClass newsLetter on DB.

	}

}
