package server.operations;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import server.entities.Timetable;
import server.entities.TimetableLesson;
import server.exceptions.ScheduleCreationException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Printer class to create the schedule as PDF / PNG file.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class FilePrinter {

	public final File printAsPDF(final Timetable timeTable)
			throws ScheduleCreationException {
		final Document document = new Document();
		String path = null;
		try {

			path = System.getProperty("user.home")
					+ System.getProperty("file.separator") + "timeTable.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			document.add(new Paragraph(this.createText(timeTable)));
			document.close();

		} catch (final FileNotFoundException | DocumentException e) {
			final ScheduleCreationException e2 = new ScheduleCreationException();
			new ExceptionLogger().logException(e2);
			throw e2;
		}
		return new File(path);
	}

	public final File printAsPng(final Timetable timeTable)
			throws ScheduleCreationException {
		String path = "";
		try {
			final int width = 200, height = 200;

			final BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);

			final Graphics2D ig2 = image.createGraphics();

			final Font font = new Font("TimesRoman", Font.BOLD, 20);
			ig2.setFont(font);
			final String message = this.createText(timeTable);
			final FontMetrics fontMetrics = ig2.getFontMetrics();
			final int stringWidth = fontMetrics.stringWidth(message);
			final int stringHeight = fontMetrics.getAscent();
			ig2.setPaint(Color.black);
			ig2.drawString(message, (width - stringWidth) / 2, height / 2
					+ stringHeight / 4);

			path = System.getProperty("user.home")
					+ System.getProperty("file.separator") + "timeTable.png";

			ImageIO.write(image, "PNG", new File(path));

		} catch (final IOException e) {
			final ScheduleCreationException e2 = new ScheduleCreationException();
			new ExceptionLogger().logException(e2);
			throw e2;
		}
		return new File(path);

	}

	private String createText(final Timetable timeTable) {

		final StringBuilder sb = new StringBuilder();

		for (final TimetableLesson lesson : timeTable.getLessons()) {
			sb.append(lesson.getRoom());
		}
		// TODO fill file with timeTable data.
		return sb.toString();
	}
}
