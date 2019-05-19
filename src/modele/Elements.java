package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Cette classe correspond à un élément.
 * Il est défini par un code, un nom, une quantité en stock, une unité de mesure (litres, kilos, ...), un prix d'achat et un prix de vente.
 */
public class Elements {
    private StringProperty code;
    private StringProperty nom;
    private DoubleProperty quantite;
    private StringProperty unite;
    private DoubleProperty prixAchat;
    private DoubleProperty prixVente;

    /**
     * Cette méthode permet de construire un élément.
     * @param code Correspond au code alphanumérique de l'élément.
     * @param nom Correspond au nom de l'élément.
     * @param quantite Correspond à la quantité en stock de l'élément.
     * @param unite Correspond à l'unité dans laquelle est exprimée la quantité
     * @param prixAchat Correspond au prix d'achat de l'élément, il est à -1 s'il est non achetable.
     * @param prixVente Correspond au prix de vente de l'élément, il est à -1 s'il est non vendable.
     */
    public Elements(String code, String nom, double quantite, String unite, double prixAchat, double prixVente){
        this.code=new SimpleStringProperty(code);
        this.nom=new SimpleStringProperty(nom);
        this.quantite=new SimpleDoubleProperty(quantite);
        this.unite=new SimpleStringProperty(unite);
        this.prixAchat=new SimpleDoubleProperty(prixAchat);
        this.prixVente=new SimpleDoubleProperty(prixVente);
    }

    /**
     * @return Cette méthode renvoie les informations de l'élément mises en forme.
     */
    public String toString(){
        return ("Element "+this.code+" "+this.nom+", quantité: "+this.quantite+" "+this.unite+", prix d'achat: "+this.prixAchat+", prix de vente: "+this.prixVente);
    }

    /**
     * Cette méthode permet de remplacer la quantité en stock.
     * @param quantite Correspond à la quantité qui doit remplacer la quantité actuelle.
     */
    public void setQuantite(double quantite) {
        if(quantite<0){
            System.out.println("La quantité ne peut pas être négative!\nValeur par defaut à 0");
            this.quantite.setValue(0);
        }
        else{
            this.quantite.setValue(quantite);
        }
    }

    /**
     * @return Cette méthode renvoie le code de l'élément.
     */
    public StringProperty getCode() {
        return code;
    }

    /**
     * @return Cette méthode renvoie le nom de l'élément.
     */
    public StringProperty getNom() {
        return nom;
    }

    /**
     * @return Cette méthode renvoie la quantité en stock de l'élément.
     */
    public DoubleProperty getQuantite() {
        return quantite;
    }

    /**
     * @return Cette méthode renvoie l'unité de mesure de l'élément.
     */
    public StringProperty getUnite() {
        return unite;
    }

    /**
     * @return Cette méthode renvoie le prix d'acaht de l'élément.
     */
    public DoubleProperty getPrixAchat() {
        return prixAchat;
    }

    /**
     * @return Cette méthode renvoie le prix de vente de l'élément.
     */
    public DoubleProperty getPrixVente() {
        return prixVente;
    }
}
