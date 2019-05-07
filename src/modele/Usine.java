package modele;




import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Usine {

    private ArrayList<ChaineProduction> listeChaine = new ArrayList<>();
    private Stockage stockage;
    private Fichier element;
    private Fichier chaines;
    private Fichier employes;
    private ArrayList<ChaineProduction> chaineProd;
    private ListeAchat listeAchat=new ListeAchat();
    private ArrayList<Employes> listEmployes;

    public Usine(){
    }

    /**
     * Chargement des données des fichiers csv fournis par l'utilisateur
     * @param pathElements chemin d'accès (absolu) du fichier éléments
     * @param pathChaines chemin d'accès (absolu) du fichier chaine
     * @return un booleen indiquant si les fichiers ont bien été chargé
     */
    public boolean loadFichier(String pathElements, String pathChaines, String pathEmployes){
        this.element=new Fichier(pathElements);
        this.chaines=new Fichier(pathChaines);
        this.employes=new Fichier(pathEmployes);

        if(!(this.element.loadData()) || !(this.chaines.loadData()) || !(this.employes.loadData())){
            return(false);
        }
        return false;
    }

    public ArrayList<ChaineProduction> getChaineProd(){
        return this.chaineProd;
    }

    /*Gestion du stockage*/

    /**
     * Création du stockage à partir du fichier csv élément
     */
    public void creationStockage(){
        this.stockage=new Stockage(this.element.getData());
    }

    public void accèsDonnéeStockage(){

    }

    public void creationChaines(){
        ArrayList<ChaineProduction> chainesProd = new ArrayList<>();
        for (String[] elem : this.chaines.getData()) {
            chainesProd.add(new ChaineProduction(elem[0], elem[1], 0, elem[2], elem[3], Integer.parseInt(elem[4]), Integer.parseInt(elem[5]), Integer.parseInt(elem[6])));
        }
        this.chaineProd = chainesProd;
    }

    public void creationEmployes(){
        ArrayList<Employes> listEmployes = new ArrayList<>();
        for (String[] elem : this.employes.getData()){
            if(elem[1].equals("y")){
                listEmployes.add(new Employes(elem[0],true, Integer.parseInt(elem[2])));
            }else{
                listEmployes.add(new Employes(elem[0],false, Integer.parseInt(elem[2])));
            }

        }

        this.listEmployes=listEmployes;
    }

    public Map<String, String> calculConso() {
        Map<String, String> dico = new HashMap<String, String>();
        for (int i = 0; i < this.chaineProd.size(); i++) {
            if (this.chaineProd.get(i).getNivActivation().getValue() > 0) {
                for (int j = 0; j < this.chaineProd.get(i).getElemProd().get(0).flux.size(); j++) {
                    if (dico.containsKey(this.chaineProd.get(i).getElemProd().get(0).flux.get(j))) {
                        IntegerProperty temp=new SimpleIntegerProperty((this.chaineProd.get(i).getElemProd().get(0).flux.get(j).quantiteProperty().multiply(this.chaineProd.get(i).getNivActivation())).getValue().intValue());
                        temp=new SimpleIntegerProperty(temp.add(Integer.parseInt(dico.get(this.chaineProd.get(i).getElemProd().get(0).flux.get(j)))).getValue());
                        dico.replace(this.chaineProd.get(i).getElemProd().get(0).flux.get(j).getCodeElem(), String.valueOf(temp));
                    } else {
                        dico.put(this.chaineProd.get(i).getElemProd().get(0).flux.get(j).getCodeElem(), String.valueOf(this.chaineProd.get(i).getElemProd().get(0).flux.get(j).getQuantite()*this.chaineProd.get(i).getNivActivation().getValue()));
                    }
                }
            }

        }
        return dico;
    }

    public boolean verificationChaine() {
        boolean chainePossible = true;
        Map<String, String> conso = new HashMap<String, String>();
        conso = this.calculConso();
        Map<String, String> prod = new HashMap<>();
        prod = this.calculProduction();
        Stockage stock = this.stockage;

        for (int i = 0; i < stock.getStock().size(); i++) {
            if (prod.containsKey(stock.getStock().get(i).getCode().getValue())) {
                double produit = Double.parseDouble(prod.get(stock.getStock().get(i).getCode().getValue()));
                stock.getStock().get(i).setQuantite(stock.getStock().get(i).getQuantite().getValue() + produit);
            }
        }

        for (int i = 0; i < stock.getStock().size(); i++) {
            if (conso.containsKey(stock.getStock().get(i).getCode().getValue())) {
                double consomme = Double.parseDouble(conso.get(stock.getStock().get(i).getCode().getValue()));
                if (consomme > stock.getStock().get(i).getQuantite().getValue()) {

                    if (stock.getStock().get(i).getPrixAchat().getValue()<0){
                        chainePossible = false;
                    }
                    else{
                        Achat newAchat = new Achat(stock.getStock().get(i),consomme, stock.getStock().get(i).getPrixAchat().getValue()*(consomme-stock.getStock().get(i).getQuantite().getValue()),  stock.getStock().get(i).getCode().getValue(), stock.getStock().get(i).getNom().getValue());
                        this.listeAchat.addAchat(newAchat);
                        this.listeAchat.addCoutTotal(stock.getStock().get(i).getPrixAchat().getValue()*(consomme-stock.getStock().get(i).getQuantite().getValue()));
                    }
                }
            }
        }

        IntegerProperty demandeEQ= new SimpleIntegerProperty(0);
        IntegerProperty demandeENQ=new SimpleIntegerProperty(0);
        IntegerProperty offreEQ=new SimpleIntegerProperty(0);
        IntegerProperty offreENQ=new SimpleIntegerProperty(0);

        for (int i = 0; i < this.listEmployes.size(); i++) {
            if (this.listEmployes.get(i).isQualifie()){
                offreEQ.add(this.listEmployes.get(i).getNbHeure());
            }else{
                offreENQ.add(this.listEmployes.get(i).getNbHeure());
            }
        }

        for (int i = 0; i < this.chaineProd.size(); i++) {
            demandeENQ.add(this.chaineProd.get(i).getNbEmployeNonQ().multiply(this.chaineProd.get(i).getTps()).multiply(this.chaineProd.get(i).getNivActivation()));
            demandeEQ.add(this.chaineProd.get(i).getNbEmployeQ().multiply(this.chaineProd.get(i).getTps()).multiply(this.chaineProd.get(i).getNivActivation()));
        }

        if(Boolean.parseBoolean(String.valueOf((demandeEQ.greaterThan(offreEQ)).or(demandeENQ.greaterThan(offreENQ.add(offreEQ).subtract(demandeEQ)))))){
            chainePossible=false;
        }

        return chainePossible;
    }

    public Map<String, String> calculProduction() {
        Map<String, String> dico = new HashMap<String, String>();
        for (int i = 0; i < this.chaineProd.size(); i++) {
            if (this.chaineProd.get(i).getNivActivation().getValue() > 0) {
                for (int j = 0; j < this.chaineProd.get(i).getElemProd().get(1).flux.size(); j++) {
                    if (dico.containsKey(this.chaineProd.get(i).getElemProd().get(1).flux.get(j))) {
                        IntegerProperty temp=new SimpleIntegerProperty((this.chaineProd.get(i).getElemProd().get(1).flux.get(j).quantiteProperty().multiply(this.chaineProd.get(i).getNivActivation())).getValue().intValue());
                        temp=new SimpleIntegerProperty(temp.add(Integer.parseInt(dico.get(this.chaineProd.get(i).getElemProd().get(1).flux.get(j)))).getValue());
                        dico.replace(this.chaineProd.get(i).getElemProd().get(1).flux.get(j).getCodeElem(), String.valueOf(temp));
                    } else {
                        dico.put(this.chaineProd.get(i).getElemProd().get(1).flux.get(j).getCodeElem(), String.valueOf(this.chaineProd.get(i).getElemProd().get(1).flux.get(j).getQuantite()*this.chaineProd.get(i).getNivActivation().getValue()));
                    }
                }
            }

        }
        return dico;
    }

    public double benefice() {
        double revenu = 0;
        double depense = 0;
        Map<String, String> conso = new HashMap<String, String>();
        conso = this.calculConso();

        for (int i = 0; i < this.stockage.getStock().size(); i++) {
            if (this.stockage.getStock().get(i).getPrixAchat().getValue() > 0) {
                if (conso.containsKey(this.stockage.getStock().get(i).getCode().getValue())) {
                    double consomme = Double.parseDouble(conso.get(this.stockage.getStock().get(i).getCode().getValue()));
                    depense += consomme *this.stockage.getStock().get(i).getPrixAchat().getValue();
                }
                else{
                    depense+=this.stockage.getStock().get(i).getQuantite().getValue()*this.stockage.getStock().get(i).getPrixAchat().getValue();

                }
            }
        }

        depense +=listeAchat.getCoutTotal();

        Map<String, String> dico = calculProduction();
        for (int i = 0; i < this.stockage.getStock().size(); i++) {
            if(dico.containsKey(this.stockage.getStock().get(i).getCode().getValue()) && this.stockage.getStock().get(i).getPrixVente().getValue() >= 0){
                double produit = Double.parseDouble(dico.get(this.stockage.getStock().get(i).getCode().getValue()));
                revenu+=produit*this.stockage.getStock().get(i).getPrixVente().getValue();
            }


        }
        return revenu-depense;

    }

    public Stockage getStockage() {
        return stockage;
    }

    public ListeAchat getListeAchat() {
        return listeAchat;
    }

    public Fichier getEmployes() {
        return employes;
    }

    public ArrayList<Employes> getListeEmployes(){
        return this.listEmployes;
    }



    @Override
    public String toString() {
        return this.stockage.toString();
    }

}
