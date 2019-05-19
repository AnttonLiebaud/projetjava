package modele;

import javafx.beans.property.*;

/**
 * Cette classe correspond à un flux.
 * Il est défini par un code d'élément et une quantité.
 */
public class Flux {

    private StringProperty codeElem;

    private DoubleProperty quantite;

    /**
     * Cette méthode permet de construire un flux.
     * @param codeElem Correspond au code de l'élément.
     * @param quantite Correspond à la quantité.
     */
    public Flux(String codeElem, String quantite){
        this.codeElem= new SimpleStringProperty(codeElem);
        this.quantite=new SimpleDoubleProperty(Double.valueOf(quantite));
    }

    /**
     * @return Cette méthode renvoie le code de l'élément.
     */
    public String getCodeElem() {
        return codeElem.get();
    }

    /**
     * @return Cette méthode renvoie le code de l'élément.
     */
    public StringProperty codeElemProperty() {
        return codeElem;
    }

    /**
     * @return Cette méthode renvoie la quantité.
     */
    public double getQuantite() {
        return quantite.get();
    }

    /**
     * @return Cette méthode renvoie la quantité.
     */
    public DoubleProperty quantiteProperty() {
        return quantite;
    }

    /**
     * Cette méthode permet de remplacer la quantité.
     * @param quantite Correspond à la quantité qui doit remplacer la quantité actuelle.
     */
    public void setQuantite(double quantite) {
        this.quantite.set(quantite);
    }
}
