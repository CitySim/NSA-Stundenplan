package server.exceptions;

import java.util.Date;

/**
 * Used as super class for other exceptions to implement the showErrorMessage
 * method.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class SuperException extends Exception {
	private static final long serialVersionUID = 708521289592050569L;

	private int errorNumber = 0;
	private String errorTitel = "";
	private final String errorMessage = "";
	private Date date = null;

	SuperException(final int errorNumber, final String errorTitel) {
		super();
		this.errorNumber = errorNumber;
		this.errorTitel = errorTitel;
		this.date = new Date();
	}

	public String createLogingMessage() {
		final StringBuffer sb = new StringBuffer();
		sb.append(this.errorNumber);
		sb.append(this.errorTitel);
		sb.append(this.date);
		return sb.toString();
	}

	public String showErrorMessage() {

		return this.errorMessage;
	}
}
