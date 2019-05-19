package modele;

import javafx.beans.property.*;

/**
 * Cette classe correspond à un employé.
 * Il est défini par un code, s'il est qualifié ou non et un nombre d'heures de travail possible.
 */
public class Employes {

    private StringProperty code;
    private boolean qualifie;
    private IntegerProperty nbHeure;

    /**
     * Cette méthode permet de construire un employé.
     * @param code Correspond au code de l'employé.
     * @param qualifie Correspond à si l'employé est qualifié (1) ou non (0).
     * @param nbHeure Correspond au nombre d'heures que l'employé peut faire.
     */
    public Employes(String code, boolean qualifie, int nbHeure){
        this.code= new SimpleStringProperty(code);
        this.qualifie=qualifie;
        this.nbHeure= new SimpleIntegerProperty(nbHeure);
    }

    /**
     * @return Cette méthode renvoie le code de l'employé.
     */
    public StringProperty getCode() {
        return code;
    }

    /**
     * @return Cette méthode renvoie l'état de qualification de l'employé.
     */
    public boolean isQualifie() {
        return qualifie;
    }

    /**
     * @return Cette méthode renvoie l'état de la qualification de l'employé.
     */
    public StringProperty getQualifie() {  StringProperty var = new SimpleStringProperty((String) ""+ qualifie); return var;}

    /**
     * @return Cette méthode renvoie le nombre d'heures que peut effectuer l'employé.
     */
    public IntegerProperty getNbHeure() {
        return nbHeure;
    }
}
