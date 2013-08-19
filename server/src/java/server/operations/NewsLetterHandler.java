package server.operations;

import server.exceptions.EmailSendingException;
import server.operations.email.EmailJobHelper;
import server.queries.NewsletterQuery;

/**
 * Used to add newsletter entries on the database. Generates URLs for confirming the registration / removing of eMailAddresses.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class NewsLetterHandler {

    public final void addAddress(final String eMailAddress, final String schoolClass) {

        try {
            new EmailJobHelper().sendConfirmationMail(eMailAddress, schoolClass);
        } catch (final EmailSendingException e) {
            new ExceptionLogger().logException(e);
        }

    }

    public final boolean removeAddress(final String eMailAddress, final String schoolClass) {

        return new NewsletterQuery().removeEmail(eMailAddress, schoolClass);

    }

    public final String generateRegistrationLink(final String schoolClass, final String eMailAddress) {

        final String url = "nsa blabla/" + "add_" + eMailAddress + "_to:" + schoolClass;

        // createLink containing our webSite URL + add + emailAddress + to
        // schoolClass

        return url;
    }

    public final String generateRemoveLink(final String schoolClass, final String eMailAddress) {

        final String url = "nsablabla/" + "remove_" + eMailAddress + "_from:" + schoolClass;

        // createLink containing our webSite URL + remove + emailAddress + to
        // schoolClass

        return url;
    }

    public final boolean validateConfirmation(final String url) {

        String eMailAddress = null;
        String schoolClass = null;

        if (url.contains("add")) {

            eMailAddress = url.substring(url.indexOf("_") + 1, url.lastIndexOf("_"));
            schoolClass = url.substring(url.lastIndexOf(":") + 1, url.length());

            // boolean check
            new NewsletterQuery().addEmail(eMailAddress, schoolClass);
            return true;

        } else if (url.contains("remove")) {

            eMailAddress = url.substring(url.indexOf("_") + 1, url.lastIndexOf("_"));
            schoolClass = url.substring(url.lastIndexOf(":") + 1, url.length());

            return new NewsletterQuery().removeEmail(eMailAddress, schoolClass);
        }
        return false;
    }
}
