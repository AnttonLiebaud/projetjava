package modele;

import java.util.ArrayList;


/**
 * Cette classe correspond aux éléments de production.
 * Ils sont définis par une liste de flux.
 */
public abstract class ElementsProductions {

    public ArrayList<Flux> flux = new ArrayList<Flux>();//liste des elements entrants ou sortants, indice pair=code element, indice impair=quantité

    /**
     * Cette méthode permet d'intégrer les données de production des fichiers dans l'application et ainsi de créer les éléments de production.
     * @param data Correspond aux données présentes dans le fichier contenant les éléments de production.
     */
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
     * Cette méthode calcule les éléments utilisés durant la production.
     * @param nivAct Correspond au niveau d'activation de la chaîne de production associée.
     * @return Cette méthode renvoie la liste de ce qui a été produit ou consommé.
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


    /**
     * @return Cette méthode renvoie les informations concernant les éléments de production mises en forme.
     */
    public String toString() {
        String affiche = "";
        for (int i = 0; i < this.flux.size(); i++) {
            affiche = affiche + ";" + this.flux.get(i);
        }
        return affiche;
    }
}
