package server.exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionLogger {

	public static void main(final String[] args) throws IOException {

		new ExceptionLogger().logException(new EmailSendingException());
	}

	public void logException(final SuperException exception) {

		final String path = System.getProperty("user.home")
				+ System.getProperty("file.separator")
				+ "NSA_StundenPlan_log.txt";
		try {
			final FileReader file = new FileReader(path);
			if (file != null) {
				final BufferedReader reader = new BufferedReader(file);
				System.out.println(reader.readLine());
			}
		} catch (final java.io.FileNotFoundException e) {
			System.out.println("FileNotFound");
		} catch (final IOException e) {
			e.printStackTrace();
		}
		try {
			final PrintWriter writer = new PrintWriter(new FileWriter(path));
			writer.println(exception.getMessage());
			writer.flush();
			writer.close();

		} catch (final IOException e) {
			e.printStackTrace();
		}

	}
}
