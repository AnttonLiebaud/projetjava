package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Cette classe correspond à un élément à acheter pour faire fonctionner la chaîne de production.
 * Il est défini par un numéro, un nom, une quantité et un prix d'achat.
 */
public class Achat {



    private Elements element;
    private StringProperty numElement;
    private StringProperty nomElement;
    private StringProperty quantite;
    private StringProperty cout;

    /**
     * Cette méthode permet de construire un élément achetable.
     * @param element Ce paramètre correspond à l'élément qu'il est nécessaire d'acheter pour faire fonctionner la chaîne de production.
     * @param quantite Ce paramètre correspond à la quantité à acheter.
     * @param cout Ce paramètre correspond au prix de l'élément.
     * @param num Ce paramètre correspond au numéro de l'élément.
     * @param nom Ce paramètre correspond au nom de l'élément.
     */
    public Achat(Elements element, double quantite, double cout, String num, String nom) {
        this.element = element;
        this.quantite = new SimpleStringProperty(""+quantite);
        this.cout = new SimpleStringProperty(""+cout);
        this.numElement = new SimpleStringProperty(num);
        this.nomElement = new SimpleStringProperty(nom);

    }

    /**
     * @return Cette méthode renvoie l'élément à acheter.
     */
    public Elements getElement() {
        return element;
    }

    /**
     * Cette méthode permet de remplacer l'élément à acheter.
     * @param element Correspond à l'élément qui doit remplacer l'élément actuel.
     */
    public void setElement(Elements element) {
        this.element = element;
    }

    /**
     * @return Cette méthode renvoie le numéro de l'élément à acheter.
     */
    public String getNumElement() {
        return numElement.get();
    }

    /**
     * @return Cette méthode renvoie le numéro de l'élément à acheter.
     */
    public StringProperty numElementProperty() {
        return numElement;
    }

    /**
     * Cette méthode permet de remplacer le numéro de l'élément.
     * @param numElement Correspond au numéro qui doit remplacer le numéro actuel.
     */
    public void setNumElement(String numElement) {
        this.numElement.set(numElement);
    }

    /**
     * @return Cette méthode renvoie le nom de l'élément.
     */
    public String getNomElement() {
        return nomElement.get();
    }

    /**
     * @return Cette méthode renvoie le nom de l'élément.
     */
    public StringProperty nomElementProperty() {
        return nomElement;
    }

    /**
     * Cette méthode permet de remplacer le nom de l'élément.
     * @param nomElement Correspond au nom qui doit remplacer le nom actuel.
     */
    public void setNomElement(String nomElement) {
        this.nomElement.set(nomElement);
    }

    /**
     * @return Cette méthode renvoie la quantité à acheter.
     */
    public String getQuantite() {
        return quantite.get();
    }

    /**
     * @return Cette méthode renvoie la quantité à acheter.
     */
    public StringProperty quantiteProperty() {
        return quantite;
    }

    /**
     * Cette méthode permet de remplacer la quantité à acheter.
     * @param quantite Correspond à la quantité qui doit remplacer la quantité actuelle.
     */
    public void setQuantite(String quantite) {
        this.quantite.set(quantite);
    }

    /**
     * @return Cette méthode renvoie le prix d'achat.
     */
    public String getCout() {
        return cout.get();
    }

    /**
     * @return Cette méthode renvoie le prix d'achat.
     */
    public StringProperty coutProperty() {
        return cout;
    }

    /**
     * Cette méthode permet de remplacer le prix d'achat.
     * @param cout Correspond au prix qui doit remplacer le prix actuel.
     */
    public void setCout(String cout) {
        this.cout.set(cout);
    }
}
