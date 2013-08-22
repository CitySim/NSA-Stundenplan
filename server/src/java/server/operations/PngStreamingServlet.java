package server.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.exceptions.ScheduleCreationException;

/**
 * Servlet used for streaming the schedule as png.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class PngStreamingServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest request,
			final HttpServletResponse response) {
		try {
			this.createPng(request, response);
		} catch (final ScheduleCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected final void doPost(final HttpServletRequest request,
			final HttpServletResponse response) {
		try {
			this.createPng(request, response);
		} catch (final ScheduleCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createPng(final HttpServletRequest request,
			final HttpServletResponse response)
			throws ScheduleCreationException {

		final File pngFile = new File(new FilePrinter().printAsPng());

		response.setContentType("application/png");
		response.addHeader("Content-Disposition", "attachment; filename="
				+ "stundenplan.png");
		response.setContentLength((int) pngFile.length());

		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(pngFile);

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