package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

/**
 * Cette classe correspond à une chaine de production.
 * Elle est définie par un code, un nom, un niveau d'activation, un temps d'exécution, un nombre d'employés qualifiés et un nombre d'employés non qualifiés
 * nécessaires à l'exécution de la chaîne de production et une liste des éléments nécessaires ou étant produits.
 */
public class ChaineProduction {
    private StringProperty code;
    private StringProperty nom;
    private IntegerProperty nivActivation;
    private IntegerProperty tps;
    private IntegerProperty nbEmployeQ;
    private IntegerProperty nbEmployeNonQ;
    private ArrayList<ElementsProductions> elemProd= new ArrayList<>();//0:Entree,1:Sortie

    /**
     * @return Cette méthode renvoie le code de la chaîne de production.
     */
    public StringProperty getCode() {
        return code;
    }

    /**
     * @return Cette méthode renvoie le nom de la chaîne de production.
     */
    public StringProperty getNom() {
        return nom;
    }


    /**
     *Cette méthode permet de construire une chaîne de production.
     * @param code Correspond au code alphanumérique de la chaine de production.
     * @param nom Correspond au nom de la chaine de production.
     * @param entree Correspond aux données de flux de l'entrée de la chaine
     * @param sortie Correspond aux données de flux de la sortie de la chaine
     */
    public ChaineProduction(String code,String nom,int nivActivation,String entree, String sortie,int tps, int nbEQ, int nbENQ){
        this.code=new SimpleStringProperty(code);
        this.nom= new SimpleStringProperty(nom);
        this.nivActivation= new SimpleIntegerProperty(nivActivation);
        this.elemProd.add(new EntreeProduction(entree));
        this.elemProd.add(new SortieProduction(sortie));
        this.tps=new SimpleIntegerProperty(tps);
        this.nbEmployeQ=new SimpleIntegerProperty(nbEQ);
        this.nbEmployeNonQ=new SimpleIntegerProperty(nbENQ);
    }

    /**
     * @return Cette méthode renvoie la liste des éléments necessaires ou produits par la chaîne.
     */
    public ArrayList<ElementsProductions> getElemProd() {
        return this.elemProd;
    }

    /**
     * @return Cette méthode renvoie le niveau d'activation de la chaîne de production.
     */
    public IntegerProperty getNivActivation() {
        return this.nivActivation;
    }

    /**
     * Cette méthode permet de remplacer le niveau d'activation de la chaîne de production, qui doit être positif, 0 correspondant à une chaine ne produisant pas.
     * @param nivActivation Correspond au niveau d'activation de la chaine de production, définit par l'utilisateur, doit être positif.
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

    /**
     * @return Cette méthode renvoie les informations de la chaîne de production mises en forme.
     */
    public String toString() {
        return "Code cp: " + this.code + "; nom cp: " + this.nom + "; niveau d'activation: " + this.nivActivation + "; éléments en entrée et en sortie: " + this.elemProd.toString();
    }

    /**
     *
     *
     *
     * Normal que cette méthode fasse rien?
     *
     *
     *
     *
     * @param etatStock
     */
    public void production(String[] etatStock){

    }

    /**
     * @return Cette méthode renvoie le temps d'exécution de la chaîne de production.
     */
    public IntegerProperty getTps() {
        return tps;
    }

    /**
     * @return Cette méthode renvoie le nombre d'employés qualifiés nécessaires à la chaîne de production.
     */
    public IntegerProperty getNbEmployeQ() {
        return nbEmployeQ;
    }

    /**
     * @return Cette méthode renvoie le nombre d'employés non qualifiés nécessaires à la chaîne de production.
     */
    public IntegerProperty getNbEmployeNonQ() {
        return nbEmployeNonQ;
    }
}