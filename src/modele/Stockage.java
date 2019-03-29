package modele;

import java.util.ArrayList;

public class Stockage {

    private ArrayList<Elements> stock= new ArrayList<>();

    /**
     * Crée l'ensemble des éléments à partir des données fournies et stock dans une liste ces éléments
     * @param anhdjt Donnée contenu le fichier csv elements. Récupérer dans la classe fichier et envoyé par Usine.
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

    public ArrayList<Elements> getStock() {
        return stock;
    }

    /**
     * redéfinition de toString
     * @return les informations de l'ensemble des éléments du stock
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
