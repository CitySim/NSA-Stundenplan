package server.operations;

import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailSettings;

/**
 * Helper class used to initialize the emailSending and to create the emails to
 * send.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class EmailJobHelper {

	private EmailSettings setEmailSettings() {
		return new EmailSettings("nsa-stundenplan@gmx.de", "nsa-stundenplan",
				"nsa-stundenplan@gmx.de", "NSA - Stundenplan Abweichung",
				"smtp.gmx.net");
	}

	public final void sendMailToGroups() {

		// if (groupList.size() == 0) {
		// new NothingToDoExeption("Send").showDialog();
		// return;
		// }

		// final ArrayList<EmailObject> emailList =
		// this.createEmailObjects(pojo,
		// groupList);
		// new EmailJob().sendMail(this.setEmailSettings(), emailList);
	}

	// private ArrayList<EmailObject> createEmailObjects(final Pojo pojo,
	// final ArrayList<Group> groupList) {
	//
	// final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();
	// final String path = pojo.getSettings().getPath();
	//
	// for (final Group group : groupList) {
	//
	// final EmailObject emailObject = new EmailObject();
	// emailList.add(emailObject);
	//
	// final ArrayList<String> emailAddresList = emailObject
	// .getEmailAddressList();
	// final String emailText = new TextCreator().generateMailText(group,
	// path);
	// final File file = new File(path + "Groups//" + group.getName()
	// + ".xml");
	//
	// new EmailContentCreator().createMailContent(emailText, file,
	// emailObject);
	//
	// for (final Member member : group.getMemberList()) {
	// emailAddresList.add(member.getEMailAdress());
	// }
	// }
	// return emailList;
	// }

}
