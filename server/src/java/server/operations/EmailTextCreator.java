package server.operations;

/**
 * Class for generating the text for the schedule mails.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

class EmailTextCreator {

	String generateMailText() {

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

	String generateConformationText() {

		final StringBuilder sb = new StringBuilder();

		sb.append("Hallo,");
		sb.append("Sie haben sich für den Newsletter der Nation Stundenplan Agency registriert.");
		sb.append(System.lineSeparator());

		sb.append("Bitte bestätigen sie ihre Anmeldung indem Sie auf den folgenden Link klicken:");
		// sb.append("link");
		sb.append(System.lineSeparator());

		sb.append("Sollten Sie die Registrierung nicht durchgeführt haben oder keine Newsletter erhalten wollen,");
		sb.append("klicken Sie auf den folgenden Link oder ignorieren diese Nachricht.");
		// sb.append("link");
		sb.append("Ihre Addresse wird dann in kürze wieder gelöscht.");

		sb.append(System.lineSeparator());
		sb.append("Ihr National Studenplan Agency Team.");

		return sb.toString();

	}
}
