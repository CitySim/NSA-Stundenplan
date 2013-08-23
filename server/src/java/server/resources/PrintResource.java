package server.resources;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.exceptions.ScheduleCreationException;
import server.operations.FilePrinter;

@Path("print")
public class PrintResource {

	@GET
	@Path("pdf")
	@Produces(MediaType.TEXT_PLAIN)
	public File printPDF() {
		FilePrinter filePrinter = new FilePrinter();
		File pdf = null;
		try {
			pdf = filePrinter.printAsPDF(new TimetableResource().getClassTimetable(1));
		} catch (ScheduleCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pdf;
	}

	@GET
	@Path("png")
	@Produces(MediaType.TEXT_PLAIN)
	public File printPNG() {
		FilePrinter filePrinter = new FilePrinter();
		File png = null;
		try {
			png = filePrinter.printAsPng(new TimetableResource().getClassTimetable(1));
		} catch (ScheduleCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return png;
	}
}