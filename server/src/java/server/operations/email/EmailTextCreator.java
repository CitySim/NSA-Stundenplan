package server.operations.email;

import server.entities.Form;
import server.entities.Newsletter;
import server.operations.NewsLetterHandler;

/**
 * Class for generating the text for the schedule mails.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

class EmailTextCreator {

	String generateScheduleChangeText() {

		final StringBuilder sb = new StringBuilder();

		sb.append("Hallo,");
		sb.append(System.lineSeparator());
		sb.append("für den");
		sb.append("system.getTag + den + system.getDATUM");
		sb.append("hat sich eine Abweichung ergeben.");
		sb.append(System.lineSeparator());

		sb.append("Betroffene Stunde(n) [Font größer]");
		sb.append("Stunde | Fach | Lehrer");
		sb.append("alt:");
		sb.append("geändert:");
		sb.append(System.lineSeparator());

		sb.append("Änderungsart: system.get (?) Ausfall / Vertretung / Verschiebung");
		sb.append(System.lineSeparator());
		sb.append("Hinweis: Hierbei handelt es sich um eine einmalige Änderung.");
		sb.append("Diese hat keinerlei Einfluss auf den regulären Stundenplan.");
		sb.append("Bitte beachten Sie, dass sich weitere Anpassungen ergeben könnten.");
		sb.append("Sie werden in diesem Fall erneut von uns benachrichtig.");
		sb.append(System.lineSeparator());
		sb.append("Wir entschuldigen uns vielmals für die Unannehmlichkeiten.");
		sb.append("Ihr National Studenplan Agency Team.");

		return sb.toString();
	}

	String generateConformationText(final Form form, final String eMailAddress) {

		final StringBuilder sb = new StringBuilder();

		sb.append("Hallo,");
		sb.append("sie haben sich für den Newsletter der Nation Stundenplan Agency registriert.");
		sb.append("Die für sie eingetragene Klasse ist die " + form.getDescription() + " .");
		sb.append(System.lineSeparator());

		sb.append("Bitte bestätigen sie ihre Anmeldung indem Sie auf den folgenden Link klicken:");
		sb.append(new NewsLetterHandler().generateRegistrationLink(form, eMailAddress));
		sb.append(System.lineSeparator());

		sb.append("Sollten Sie die Registrierung nicht durchgeführt haben oder keine Newsletter erhalten wollen,");
		sb.append("ignorieren diese Nachricht.");
		sb.append("Ihre Addresse wird dann in kürze wieder gelöscht.");

		sb.append(System.lineSeparator());
		sb.append("Ihr National Studenplan Agency Team.");

		return sb.toString();
	}

	String generateUserCreationText(final String userName, final String password) {

		final StringBuilder sb = new StringBuilder();

		sb.append("Hallo,");
		sb.append("sie wurden als Administrator der Nation Stundenplan Agency registriert.");
		sb.append(System.lineSeparator());

		sb.append("Ihr Login ist: ");
		sb.append(userName);
		sb.append(".");
		sb.append(System.lineSeparator());

		sb.append("Ihr Passwort ist: ");
		sb.append(password);
		sb.append(".");
		sb.append(System.lineSeparator());

		sb.append(System.lineSeparator());
		sb.append("Ihr National Studenplan Agency Team.");

		return sb.toString();
	}

	public String generatePasswordChangeText(final String userName, final String password) {
		final StringBuilder sb = new StringBuilder();

		sb.append("Hallo,");
		sb.append("ihr passwort wurde erfolgreich geändert.");
		sb.append(System.lineSeparator());

		sb.append("Ihr neues Passwort ist: ");
		sb.append(password);
		sb.append(".");
		sb.append(System.lineSeparator());

		sb.append(System.lineSeparator());
		sb.append("Ihr National Studenplan Agency Team.");

		return sb.toString();
	}

	public String generateRemoveRegistrationText(Newsletter newsletter) {

		final StringBuilder sb = new StringBuilder();

		sb.append("Hallo,");
		sb.append("um sich von dem Newsletter von der Klasse " + newsletter.getForm().getDescription() + "  abzumelden klicken Sie folgenden Link:");
		sb.append(System.lineSeparator());

		sb.append(new NewsLetterHandler().generateRemoveLink(newsletter));

		sb.append(System.lineSeparator());
		sb.append("Ihr National Studenplan Agency Team.");

		return sb.toString();
	}
}