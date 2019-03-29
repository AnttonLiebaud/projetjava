package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Fichier {

    private String path;
    private ArrayList<String[]> data= new ArrayList<>();

    Fichier(String path){
        this.path=path;
    }

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

    ArrayList<String[]> getData() {
        return data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
