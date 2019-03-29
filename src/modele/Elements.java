package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Elements {
    private StringProperty code;
    private StringProperty nom;
    private DoubleProperty quantite;
    private StringProperty unite;
    private DoubleProperty prixAchat;
    private DoubleProperty prixVente;

    /**
     * constructeur de Elements
     * définit à partir des informations données par le stockage
     * @param code code alphanumérique de l'élément
     * @param nom nom de l'élément
     * @param quantite quantité en stock de l'élément
     * @param unite unité dans laquelle est exprimée la quantité
     * @param prixAchat -1 si non achetable
     * @param prixVente -1 si non vendable
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
     * redfinition de toString
     * @return description de l'élément
     */
    public String toString(){
        return ("Element "+this.code+" "+this.nom+", quantité: "+this.quantite+" "+this.unite+", prix d'achat: "+this.prixAchat+", prix de vente: "+this.prixVente);
    }

    public void setQuantite(double quantite) {
        if(quantite>this.quantite.getValue()){
            System.out.println("La quantité ne peut pas être négative!\nValeur par defaut à 0");
            this.quantite.setValue(0);
        }
        else{
            this.quantite.setValue(quantite);
        }
    }

    public StringProperty getCode() {
        return code;
    }

    public StringProperty getNom() {
        return nom;
    }

    public DoubleProperty getQuantite() {
        return quantite;
    }

    public StringProperty getUnite() {
        return unite;
    }

    public DoubleProperty getPrixAchat() {
        return prixAchat;
    }

    public DoubleProperty getPrixVente() {
        return prixVente;
    }
}
