package modele;

import java.util.ArrayList;


public abstract class ElementsProductions {

    public String[] flux;//liste des elements entrants ou sortants, indice pair=code element, indice impair=quantité

    public ElementsProductions(String data){
        data=data.replaceAll("[()]",""); //regex à verifier
        data = data.replaceAll(" ", "");
        this.flux=data.split(",");

    }



    /**
     * Calcul les éléments utilisés durant la production renvoie la liste de se qui a été produit ou consommer
     * @param nivAct niveau d'activation de la chaîne de production associé
     * @return renvoie la liste de se qui a été produit ou consommer
     */
    protected ArrayList<String[]> evaluation(int nivAct){
        ArrayList<String[]> production = new ArrayList<String[]>();
        String[] buffer=new String[2];
        for (int i = 0; i < this.flux.length; i=i+2) {
            buffer[0]=this.flux[i];
            buffer[1]=String.valueOf(Float.parseFloat(this.flux[i+1])*nivAct);
            production.add(buffer);
        }
        System.out.println(production);
        return(production);
    }


    public String toString() {
        String affiche = "";
        for (int i = 0; i < this.flux.length; i++) {
            affiche = affiche + ";" + this.flux[i];
        }
        return affiche;
    }
}
