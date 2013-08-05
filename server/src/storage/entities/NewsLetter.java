package storage.entities;


public class NewsLetter {

    private int idNewsLetter;
    private int klassenID;

    // private ArrayList<EmailAddresse> eMailAddressList;

    public int getIdNewsLetter() {
        return this.idNewsLetter;
    }

    public void setIdNewsLetter(final int idNewsLetter) {
        this.idNewsLetter = idNewsLetter;
    }

    public int getKlassenID() {
        return this.klassenID;
    }

    public void setKlassenID(final int klassenID) {
        this.klassenID = klassenID;
    }

    // public ArrayList<EmailAddresse> geteMailAddressList() {
    // return this.eMailAddressList;
    // }
    //
    // public void seteMailAddressList(final ArrayList<EmailAddresse> eMailAddressList) {
    // this.eMailAddressList = eMailAddressList;
    // }

}
