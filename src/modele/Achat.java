package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Achat {



    private Elements element;
    private StringProperty numElement;
    private StringProperty nomElement;
    private StringProperty quantite;
    private StringProperty cout;

    public Achat(Elements element, double quantite, double cout, String num, String nom) {
        this.element = element;
        this.quantite = new SimpleStringProperty(""+quantite);
        this.cout = new SimpleStringProperty(""+cout);
        this.numElement = new SimpleStringProperty(""+num);
        this.nomElement = new SimpleStringProperty(""+nom);

    }

    public Elements getElement() {
        return element;
    }

    public void setElement(Elements element) {
        this.element = element;
    }

    public String getNumElement() {
        return numElement.get();
    }

    public StringProperty numElementProperty() {
        return numElement;
    }

    public void setNumElement(String numElement) {
        this.numElement.set(numElement);
    }

    public String getNomElement() {
        return nomElement.get();
    }

    public StringProperty nomElementProperty() {
        return nomElement;
    }

    public void setNomElement(String nomElement) {
        this.nomElement.set(nomElement);
    }

    public String getQuantite() {
        return quantite.get();
    }

    public StringProperty quantiteProperty() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite.set(quantite);
    }

    public String getCout() {
        return cout.get();
    }

    public StringProperty coutProperty() {
        return cout;
    }

    public void setCout(String cout) {
        this.cout.set(cout);
    }
}
