
package modele;

import java.util.ArrayList;

/**
 * Cette classe correspond à une liste d'achat.
 * Elle est définie par une liste d'élément à acheter et par un coût total.
 */
public class ListeAchat {


    ArrayList<Achat> achat = new ArrayList<>();
    int coutTotal;

    /**
     * Cette méthode construit la liste d'achat.
     * Elle initie le coût total à zéro.
     */
    public ListeAchat() {
        this.coutTotal = 0;
    }

    /**
     * @return Cette méthode renvoie la liste d'élément à acheter.
     */
    public ArrayList<Achat> getAchat() {
        return achat;
    }

    /**
     * Cette méthode permet d'ajouter un élément à acheter à la liste d'achat.
     * @param achat Correspond à un élément à acheter.
     */
    public void addAchat(Achat achat) {
        this.achat.add(achat);
    }

    /**
     * @return Cette méthode renvoie le coût total de la liste d'achat.
     */
    public int getCoutTotal() {
        return coutTotal;
    }

    /**
     * Cette méthode permet de remplacer le coût total de la liste d'achat.
     * @param coutTotal Correspond au coût d'achat qui doit remplacer le coût actuel.
     */
    public void setCoutTotal(int coutTotal) {
        this.coutTotal = coutTotal;
    }

    /**
     * Cette méthode permet d'ajouter un montant au coût total.
     * @param valeur Correspond à la valeur à ajouter au coût total de la liste d'achat.
     */
    public void addCoutTotal(double valeur){
        this.coutTotal += valeur;
    }

}


