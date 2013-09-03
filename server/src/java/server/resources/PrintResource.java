package server.resources;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import server.exceptions.ScheduleCreationException;
import server.operations.FilePrinter;

@Path("print")
public class PrintResource {

	@GET
	@Path("pdf")
	@Produces("application/pdf")
	public synchronized Response printPDF(@QueryParam("id") final int timetableId) {
		final FilePrinter filePrinter = new FilePrinter();
		File pdf = null;
		try {
			pdf = filePrinter.printAsPDF(new TimetableResource().getTimetable(timetableId));
		} catch (final ScheduleCreationException e) {
			return Response.serverError().header("error", e).build();
		}

		return Response.ok(pdf).header("Content-Disposition", "attachment; filename=timetable.pdf").build();
	}

	@GET
	@Path("png")
	@Produces("image/png")
	public synchronized Response printPNG(@QueryParam("id") final int timetableId) {
		final FilePrinter filePrinter = new FilePrinter();
		File png = null;
		try {
			png = filePrinter.printAsPng(new TimetableResource().getTimetable(timetableId));
		} catch (final ScheduleCreationException e) {
			return Response.serverError().header("error", e).build();
		}

		return Response.ok(png).header("Content-Disposition", "attachment; filename=timetable.png").build();
	}
}
