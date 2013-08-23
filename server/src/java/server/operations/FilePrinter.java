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
import java.util.List;

import javax.imageio.ImageIO;

import server.entities.Timetable;
import server.entities.TimetableLesson;
import server.exceptions.ScheduleCreationException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Printer class to create the schedule as PDF / PNG file.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class FilePrinter {

	public final File printAsPDF(final Timetable timeTable) throws ScheduleCreationException {
		final Document document = new Document();
		String path = null;
		try {

			path = System.getProperty("user.home") + System.getProperty("file.separator") + "timeTable.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			createPdfTable(timeTable, document);
			document.close();

		} catch (final FileNotFoundException | DocumentException e) {
			throw new ScheduleCreationException();
		}
		return new File(path);
	}

	public final File printAsPng(final Timetable timeTable) throws ScheduleCreationException {
		String path = "";
		try {
			final int width = 200, height = 200;

			final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			final Graphics2D ig2 = image.createGraphics();

			final Font font = new Font("TimesRoman", Font.BOLD, 20);
			ig2.setFont(font);
			final String message = this.createText(timeTable);
			final FontMetrics fontMetrics = ig2.getFontMetrics();
			final int stringWidth = fontMetrics.stringWidth(message);
			final int stringHeight = fontMetrics.getAscent();
			ig2.setPaint(Color.black);
			ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

			path = System.getProperty("user.home") + System.getProperty("file.separator") + "timeTable.png";

			ImageIO.write(image, "PNG", new File(path));

		} catch (final IOException e) {
			throw new ScheduleCreationException();
		}
		return new File(path);

	}

	private String createText(final Timetable timeTable) {

		final StringBuilder sb = new StringBuilder();

		for (final TimetableLesson lesson : timeTable.getLessons()) {
			sb.append(lesson.getSubject().getShortName());
			sb.append("\n");
			sb.append(lesson.getTeacher().getShortName());
			sb.append("\n");
			sb.append(lesson.getRoom().getDescription());
			sb.append("\n");
			sb.append("\n");
			sb.append("\n");

		}
		return sb.toString();
	}

	private void createPdfTable(final Timetable timeTable, final Document document) throws DocumentException {

		final PdfPTable table = new PdfPTable(6); 
		table.addCell("Klasse XXX");
		table.addCell("Montag");
		table.addCell("Dienstag");
		table.addCell("Mittwoch");
		table.addCell("Donnerstag");
		table.addCell("Freitag");

		table.setHeaderRows(1);
		int counter =4;
		for (final TimetableLesson lesson : timeTable.getLessons()) {
			if(counter == 4){
				counter = 0;
				table.addCell(lesson.getLesson().getTimeFrom().toString().substring(0, 5) + "\n - \n" + lesson.getLesson().getTimeTo().toString().substring(0, 5));
			}else{
				counter++;
			}
			table.addCell(lesson.getSubject().getShortName()+"\n"+
			lesson.getTeacher().getShortName()+"\n"+
			lesson.getRoom().getDescription());
		}

		document.add(table);
	}

}
