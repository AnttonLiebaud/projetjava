
package modele;

import java.util.ArrayList;

public class ListeAchat {

    ArrayList<String[]> liste=new ArrayList<>();

    public void setListe(String[] achat) {
        this.liste.add(achat);
    }

    public ArrayList<String[]> getListe() {
        return liste;
    }


}


