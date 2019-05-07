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
    private int demandeEQ = 0;
    private int demandeENQ = 0;
    private int offreEQ = 0;
    private int offreENQ = 0;

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
            chainesProd.add(new ChaineProduction(elem[0], elem[1],Integer.parseInt(elem[7]), elem[2], elem[3], Integer.parseInt(elem[4]), Integer.parseInt(elem[5]), Integer.parseInt(elem[6])));
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
                double consomme = Double.parseDouble(conso.get(stock.getStock().get(i).getCode().getValue()))-stock.getStock().get(i).getQuantite().getValue();

                if (consomme > stock.getStock().get(i).getQuantite().getValue()) {

                    if (stock.getStock().get(i).getPrixAchat().getValue()<0){
                        chainePossible = false;
                    }
                    else{
                        Achat newAchat = new Achat(stock.getStock().get(i),consomme-stock.getStock().get(i).getQuantite().getValue(), stock.getStock().get(i).getPrixAchat().getValue()*(consomme-stock.getStock().get(i).getQuantite().getValue()),  stock.getStock().get(i).getCode().getValue(), stock.getStock().get(i).getNom().getValue());
                        this.listeAchat.addAchat(newAchat);
                        this.listeAchat.addCoutTotal(stock.getStock().get(i).getPrixAchat().getValue()*(consomme-stock.getStock().get(i).getQuantite().getValue()));
                        stock.getStock().get(i).setQuantite(0);
                    }
                }
                else {
                    stock.getStock().get(i).setQuantite(stock.getStock().get(i).getPrixAchat().getValue() - consomme);
                }
            }
        }



        for (int i = 0; i < this.listEmployes.size(); i++) {
            if (this.listEmployes.get(i).isQualifie()){
                this.offreEQ += this.listEmployes.get(i).getNbHeure().getValue();
            }else{
                this.offreENQ += this.listEmployes.get(i).getNbHeure().getValue();
            }
        }

        for (int i = 0; i < this.chaineProd.size(); i++) {
            this.demandeENQ += this.chaineProd.get(i).getNbEmployeNonQ().getValue()*this.chaineProd.get(i).getTps().getValue()*this.chaineProd.get(i).getNivActivation().getValue();
            this.demandeEQ += this.chaineProd.get(i).getNbEmployeQ().getValue()*this.chaineProd.get(i).getTps().getValue()*this.chaineProd.get(i).getNivActivation().getValue();
        }

        if(demandeEQ > offreEQ || demandeENQ > (offreENQ + (offreEQ - demandeEQ))){
            chainePossible=false;
        }else{
            if(this.demandeENQ > this.offreENQ){
                demandeEQ += demandeENQ - offreENQ;
                demandeENQ -= demandeENQ - offreENQ;
            }
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

        depense +=listeAchat.getCoutTotal();

        for (int i = 0; i < this.stockage.getStock().size(); i++) {
            if(this.stockage.getStock().get(i).getPrixVente().getValue() >= 0) {
                revenu += this.stockage.getStock().get(i).getQuantite().getValue() * this.stockage.getStock().get(i).getPrixVente().getValue();
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

    public ArrayList<ChaineProduction> getListeChaine() {
        return listeChaine;
    }

    public void setListeChaine(ArrayList<ChaineProduction> listeChaine) {
        this.listeChaine = listeChaine;
    }

    public int getDemandeEQ() {
        return demandeEQ;
    }

    public void setDemandeEQ(int demandeEQ) {
        this.demandeEQ = demandeEQ;
    }

    public int getDemandeENQ() {
        return demandeENQ;
    }

    public void setDemandeENQ(int demandeENQ) {
        this.demandeENQ = demandeENQ;
    }

    public int getOffreEQ() {
        return offreEQ;
    }

    public void setOffreEQ(int offreEQ) {
        this.offreEQ = offreEQ;
    }

    public int getOffreENQ() {
        return offreENQ;
    }

    public void setOffreENQ(int offreENQ) {
        this.offreENQ = offreENQ;
    }

    @Override
    public String toString() {
        return this.stockage.toString();
    }

}
