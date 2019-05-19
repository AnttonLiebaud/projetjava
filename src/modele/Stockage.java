package modele;

import java.util.ArrayList;

/**
 * Cette classe correspond à l'état des stocks.
 * Elle est définie par une liste d'éléments.
 */
public class Stockage {

    private ArrayList<Elements> stock= new ArrayList<>();

    /**
     * Cette méthode crée l'ensemble des éléments à partir des données fournies et stock dans une liste ces éléments
     * @param anhdjt Correspond aux données contenues dans le fichier csv elements. récupéré dans la classe Fichier et envoyé par la classe Usine.
     */
    public Stockage(ArrayList<String[]> anhdjt){
        for (String[] elem : anhdjt) {
            if (elem[5].equals("NA")) {
                elem[5] = "-1";
            }
            if (elem[4].equals("NA")) {
                elem[4] = "-1";
            }

            this.stock.add(new Elements(elem[0], elem[1], Double.parseDouble(elem[2]), elem[3], Double.parseDouble(elem[4]), Double.parseDouble(elem[5])));
        }
    }

    /**
     * @return Cette méthode renvoie la liste des éléments en stock.
     */
    public ArrayList<Elements> getStock() {
        return stock;
    }

    /**
     * @return Cette méthode renvoie les informations de l'ensemble des éléments du stock mises en forme.
     */
    @Override
    public String toString() {
        StringBuilder desc= new StringBuilder("Stock de l'usine \n");
        for (Elements elem : this.stock) {
            desc.append(elem.toString()).append(" \n");
        }
        return desc.toString();
    }
}
