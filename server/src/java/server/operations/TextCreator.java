package server.operations;

/**
 * Class for generating the text for the schedule mails.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

class TextCreator {

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
}
