package modele;

import javafx.beans.property.*;

public class Flux {

    private StringProperty codeElem;

    private DoubleProperty quantite;

    public Flux(String codeElem, String quantite){
        this.codeElem= new SimpleStringProperty(codeElem);
        this.quantite=new SimpleDoubleProperty(Double.valueOf(quantite));
    }

    public String getCodeElem() {
        return codeElem.get();
    }

    public StringProperty codeElemProperty() {
        return codeElem;
    }

    public double getQuantite() {
        return quantite.get();
    }

    public DoubleProperty quantiteProperty() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite.set(quantite);
    }
}
