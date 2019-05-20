package modele;

import javafx.beans.property.*;

import java.util.ArrayList;

public class Employes {

    private StringProperty code;
    private boolean qualifie;
    private IntegerProperty nbHeure;
    private IntegerProperty workTime;
    private ArrayList<String[]> EDT = new ArrayList<>();

    public Employes(String code, boolean qualifie, int nbHeure){
        this.code= new SimpleStringProperty(code);
        this.qualifie=qualifie;
        this.nbHeure= new SimpleIntegerProperty(nbHeure);
        this.workTime=new SimpleIntegerProperty(0);
    }

    public StringProperty getCode() {
        return code;
    }

    public boolean isQualifie() {
        return qualifie;
    }

    public StringProperty getQualifie() {  StringProperty var = new SimpleStringProperty((String) ""+ qualifie); return var;}

    public IntegerProperty getNbHeure() {
        return nbHeure;
    }

    public void setEDT(String code,float time) {
        String Stime=String.valueOf(time);
        String[] tab={code,Stime};
        this.EDT.add(tab);
    }

    public int getWorkTime() {
        return workTime.get();
    }

    public IntegerProperty workTimeProperty() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime.set(workTime);
    }
}
