package server.operations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Used to get the current Date or to parse Strings to Date objects.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class DateHelper {

	private final Calendar c = new SimpleDateFormat("dd.MM.yyyy").getCalendar();

	public DateHelper() {
		this.c.setTimeInMillis(System.currentTimeMillis());
	}

	public final String getDate() {
		final StringBuilder sb = new StringBuilder();

		sb.append(this.c.get(Calendar.DAY_OF_MONTH));
		sb.append(".");
		if (Calendar.MONTH < 10) {
			sb.append("0");
		}
		sb.append(this.c.get(Calendar.MONTH) + 1);
		sb.append(".");
		sb.append(this.c.get(Calendar.YEAR));

		return sb.toString();
	}

	public final String getFullDate() {
		final StringBuilder sb = new StringBuilder();

		sb.append(this.getDate());
		sb.append("_");
		sb.append(this.c.get(Calendar.HOUR_OF_DAY));
		sb.append(".");
		sb.append(this.c.get(Calendar.MINUTE));
		sb.append(".");
		sb.append(this.c.get(Calendar.SECOND));

		return sb.toString();
	}

	public final void addTime(final int years, final int months,
			final int days, final int hours, final int minutes,
			final int seconds) {

		this.c.add(Calendar.YEAR, years);
		this.c.add(Calendar.MONTH, months);
		this.c.add(Calendar.DAY_OF_MONTH, days);
		this.c.add(Calendar.HOUR_OF_DAY, hours);
		this.c.add(Calendar.MINUTE, minutes);
		this.c.add(Calendar.SECOND, seconds);
	}

	public final Date parseStringToDate(final String dateString) {

		try {
			return new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

}
