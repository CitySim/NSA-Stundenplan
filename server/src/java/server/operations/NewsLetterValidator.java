package server.operations;

/**
 * Used to add newsletter entries on the database. Generates URLs for confirming
 * the registration / removing of eMailAddresses.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class NewsLetterValidator {

	public final void addAddress(final String eMailAddress,
			final String schoolClass) {

		new EmailJobHelper().sendConfirmationMail(eMailAddress, schoolClass);

	}

	public final String generateRegistrationLink(final String schoolClass,
			final String eMailAddress) {

		final String url = "nsa blabla/" + "add_" + eMailAddress + "_to:"
				+ schoolClass;

		// createLink containing our webSite URL + add + emailAddress + to
		// schoolClass

		return url;
	}

	final String generateRemoveLink(final String schoolClass,
			final String eMailAddress) {

		final String url = "nsablabla/" + "remove_" + eMailAddress + "_from:"
				+ schoolClass;

		// createLink containing our webSite URL + remove + emailAddress + to
		// schoolClass

		return url;
	}

	public final void validateConfirmation(final String url) {

		String eMailAddress = null;
		String schoolClass = null;

		if (url.contains("add")) {

			eMailAddress = url.substring(url.indexOf("_") + 1,
					url.lastIndexOf("_"));
			schoolClass = url.substring(url.lastIndexOf(":") + 1, url.length());
			// add eMailAddress for schoolClass newsLetter on DB.
			return;

		} else if (url.contains("remove")) {

			eMailAddress = url.substring(url.indexOf("_") + 1,
					url.lastIndexOf("_"));
			schoolClass = url.substring(url.lastIndexOf(":") + 1, url.length());
			// remove eMailAddress from schoolClass newsLetter on DB.
			return;
		}
	}
}
