package server.operations;

import server.exceptions.EmailSendingException;
import server.operations.email.EmailJobHelper;

public class emailTest {
	public static void main(String[] args){
		EmailJobHelper helper = new EmailJobHelper();
		try {
			helper.sendPasswordChangeMail("olegscheltow@localhost", "olegscheltow", "oleg");
		} catch (EmailSendingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
