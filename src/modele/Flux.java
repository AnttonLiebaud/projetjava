package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Flux {

    private StringProperty codeElem;
    private IntegerProperty quantite;

    public Flux(String codeElem, String quantite){
        this.codeElem= new SimpleStringProperty(codeElem);
        this.quantite=new SimpleIntegerProperty(Integer.valueOf(quantite));
    }

    public String getCodeElem() {
        return codeElem.get();
    }

    public StringProperty codeElemProperty() {
        return codeElem;
    }

    public int getQuantite() {
        return quantite.get();
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }
}
