package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.exceptions.ScheduleCreationException;
import server.operations.PdfStreamingServlet;
import server.operations.PngStreamingServlet;

@Path("print")
public class PrintResource {

	@GET
	@Path("pdf")
	@Produces(MediaType.APPLICATION_JSON)
	public String printPDF() {
		PdfStreamingServlet pdfCreator = new PdfStreamingServlet();
		try {
			pdfCreator.createPdf(null, null);
		} catch (ScheduleCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "?";
	}

	@GET
	@Path("png")
	@Produces(MediaType.APPLICATION_JSON)
	public String printPNG() {
		PngStreamingServlet pngCreator = new PngStreamingServlet();
		try {
			pngCreator.createPng(null, null);
		} catch (ScheduleCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "?";
	}
}