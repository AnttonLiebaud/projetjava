
package modele;

import java.util.ArrayList;

public class ListeAchat {


    ArrayList<Achat> achat = new ArrayList<>();
    int coutTotal;

    public ListeAchat() {
        this.coutTotal = 0;
    }

    public ArrayList<Achat> getAchat() {
        return achat;
    }

    public void addAchat(Achat achat) {
        this.achat.add(achat);
    }

    public int getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(int coutTotal) {
        this.coutTotal = coutTotal;
    }

    public void addCoutTotal(double valeur){
        this.coutTotal += valeur;
    }

}


