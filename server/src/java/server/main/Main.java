package server.main;

import server.operations.PdfPrinter;

public class Main {

	public static void main(final String[] args) {
		new PdfPrinter().printAsPDF();
	}

}
