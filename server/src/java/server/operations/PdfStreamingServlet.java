package server.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet used for streaming the schedule.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class PdfStreamingServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest request,
			final HttpServletResponse response) {
		this.performTask(request, response);
	}

	@Override
	protected final void doPost(final HttpServletRequest request,
			final HttpServletResponse response) {
		this.performTask(request, response);
	}

	private void performTask(final HttpServletRequest request,
			final HttpServletResponse response) {

		final File pdfFile = new File(new FilePrinter().printAsPDF());

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename="
				+ "stundenplan.pdf");
		response.setContentLength((int) pdfFile.length());

		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(pdfFile);

			final OutputStream responseOutputStream = response
					.getOutputStream();
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}
			fileInputStream.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}