package modele;

import javafx.beans.property.*;

public class Employes {

    private StringProperty code;
    private boolean qualifie;
    private IntegerProperty nbHeure;

    public Employes(String code, boolean qualifie, int nbHeure){
        this.code= new SimpleStringProperty(code);
        this.qualifie=qualifie;
        this.nbHeure= new SimpleIntegerProperty(nbHeure);
    }

    public StringProperty getCode() {
        return code;
    }

    public boolean isQualifie() {
        return qualifie;
    }

    public IntegerProperty getNbHeure() {
        return nbHeure;
    }
}
