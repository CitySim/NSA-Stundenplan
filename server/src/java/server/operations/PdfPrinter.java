package server.operations;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfPrinter {

	public void printAsPDF() {
		final Document document = new Document();
		try {

			final String path = System.getProperty("user.home")
					+ System.getProperty("file.separator")
					+ "//desktop//HelloWorld.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			document.add(new Paragraph(this.createPDFText()));
			document.close();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final DocumentException e) {
			e.printStackTrace();
		}
	}

	private String createPDFText() {
		return "Hello World";
	}
}
