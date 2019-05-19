package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Cette classe correspond à un fichier. Elle permet de stocker les données contenues dans un fichier.
 * Elle est définie par le chemin du fichier et par son contenu.
 */
class Fichier {

    private String path;
    private ArrayList<String[]> data= new ArrayList<>();

    /**
     * Cette méthode permet de construire un fichier.
     * @param path Correspond au chemin d'accès du fichier.
     */
    Fichier(String path){
        this.path=path;
    }

    /**
     * Cette méthode permet d'extraire les données contenues dans le fichier.
     * @return Cette méthode renvoie un booléan informant de la bonne exécution de la méthode (true) ou non (false).
     */
    boolean loadData(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String line;
            reader.readLine();
            while((line = reader.readLine())!=null){
                String[] lineSplit = line.split(";");
                this.data.add(lineSplit);
            }
            return true;
        }
        catch (IOException e){
            System.out.println("Problème de chargement du fichier "+ this.path);
            System.out.println("Erreur: "+e);
            return false;
        }
    }

    /**
     * @return Cette méthode renvoie le contenu du fichier.
     */
    ArrayList<String[]> getData() {
        return data;
    }

    /**
     * @return Cette méthode renvoie le chemin d'accès du fichier.
     */
    public String getPath() {
        return path;
    }

    /**
     * Cette méthode permet de remplacer le chemin d'accès du fichier.
     * @param path Correspond au chemin d'accès qui doit remplacer le chemin actuel.
     */
    public void setPath(String path) {
        this.path = path;
    }
}
