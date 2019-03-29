package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class ChaineProduction {
    private StringProperty code;
    private StringProperty nom;
    private IntegerProperty nivActivation;
    private ArrayList<ElementsProductions> elemProd= new ArrayList<>();//0:Entree,1:Sortie

    public StringProperty getCode() {
        return code;
    }

    public StringProperty getNom() {
        return nom;
    }


    /**
     *Constructeur de la classe ChaineProduction
     * @param code code alphanumérique de la chaine de production
     * @param nom nom de la chaine de production
     * @param entree donnée de flux de l'entrée de la chaine
     * @param sortie donnée de flux de la sortie de la chaine
     */
    public ChaineProduction(String code,String nom,int nivActivation,String entree, String sortie){
        this.code=new SimpleStringProperty(code);
        this.nom= new SimpleStringProperty(nom);
        this.nivActivation= new SimpleIntegerProperty(nivActivation);
        this.elemProd.add(new EntreeProduction(entree));
        this.elemProd.add(new SortieProduction(sortie));
    }

    public ArrayList<ElementsProductions> getElemProd() {
        return this.elemProd;
    }

    public IntegerProperty getNivActivation() {
        return this.nivActivation;
    }

    /**
     * Permet de set le niveau d'activation de la chaîne de production, qui doit être positif, 0 correspondant à une chaine ne produisant pas
     * @param nivActivation niveau d'activation de la chaine de production, définit par l'utilisateur, doit être positif
     */
    public void setNivActivation(int nivActivation) {
        if(nivActivation>=0) {
            this.nivActivation.setValue(nivActivation);
        }
        else{
            System.out.println("Niveau d'activation de la chaine "+this.code+" "+this.nom+" incorrect, valeur par defaut à 0");
            this.nivActivation.setValue(0);
        }
    }

    public String toString() {
        return "Code cp: " + this.code + "; nom cp: " + this.nom + "; niveau d'activation: " + this.nivActivation + "; éléments en entrée et en sortie: " + this.elemProd.toString();
    }

    public void production(String[] etatStock){

    }
}