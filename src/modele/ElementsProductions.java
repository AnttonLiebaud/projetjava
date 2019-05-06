package modele;

import java.util.ArrayList;


public abstract class ElementsProductions {

    public ArrayList<Flux> flux;//liste des elements entrants ou sortants, indice pair=code element, indice impair=quantité

    public ElementsProductions(String data){
        String[] temp;
        data=data.replaceAll("[()]",""); //regex à verifier
        data = data.replaceAll(" ", "");
        temp=data.split(",");

        for (int i = 0; i < temp.length; i+=2) {
            flux.add(new Flux(temp[i],temp[i+1]));
        }

    }



    /**
     * Calcul les éléments utilisés durant la production renvoie la liste de se qui a été produit ou consommer
     * @param nivAct niveau d'activation de la chaîne de production associé
     * @return renvoie la liste de se qui a été produit ou consommer
     */
    protected ArrayList<String[]> evaluation(int nivAct){
        ArrayList<String[]> production = new ArrayList<String[]>();
        String[] buffer=new String[2];
        for (int i = 0; i < this.flux.size(); i++) {
            buffer[0]=this.flux.get(i).getCodeElem();
            buffer[1]=String.valueOf(Float.parseFloat(String.valueOf(this.flux.get(i).getQuantite()))*nivAct);
            production.add(buffer);
        }
        System.out.println(production);
        return(production);
    }


    public String toString() {
        String affiche = "";
        for (int i = 0; i < this.flux.size(); i++) {
            affiche = affiche + ";" + this.flux.get(i);
        }
        return affiche;
    }
}
