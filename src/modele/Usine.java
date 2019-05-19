package modele;




import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Cette classe correspond à une usine.
 * Elle est définie par un stock, le fichier des éléments, le fichier des chaînes de production, le fichier des employés, l'ensemble des chaînes de production,
 * une liste d'achat, des employés, un nombre d'employés qualifiés nécessaires à son bon fonctionnement, un nombre d'employés non qualifiés nécessaires à son bon fonctionnement,
 * une disponibilité d'employés qualifiés et une disponibilités d'employés non qualifiés.
 */
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

    /**
     * Cette méthode permet de construire une usine.
     */
    public Usine(){
    }

    /**
     * Cette méthode permet le chargement des données des fichiers csv fournis par l'utilisateur.
     * @param pathElements Correspond au chemin d'accès (absolu) du fichier éléments.
     * @param pathChaines Correspond au chemin d'accès (absolu) du fichier chaine.
     * @return Cette méthode renvoie un booleen indiquant si les fichiers ont bien été chargé
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

    /**
     * @return Cette méthode renvoie la chaîne de production.
     */
    public ArrayList<ChaineProduction> getChaineProd(){
        return this.chaineProd;
    }

    /*Gestion du stockage*/

    /**
     * Cette méthode permet la création du stockage à partir du fichier csv élément.
     */
    public void creationStockage(){
        this.stockage=new Stockage(this.element.getData());
    }

    /**
     *
     *
     *
     *
     * Utiles puisque vide?
     *
     *
     *
     *
     *
     */
    public void accèsDonnéeStockage(){

    }

    /**
     * Cette méthode permet de créer les chaînes de production.
     */
    public void creationChaines(){
        ArrayList<ChaineProduction> chainesProd = new ArrayList<>();
        for (String[] elem : this.chaines.getData()) {
            chainesProd.add(new ChaineProduction(elem[0], elem[1],Integer.parseInt(elem[7]), elem[2], elem[3], Integer.parseInt(elem[4]), Integer.parseInt(elem[5]), Integer.parseInt(elem[6])));
        }
        this.chaineProd = chainesProd;
    }

    /**
     * Cette méthode permet de créer les employés.
     */
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

    /**
     * Cette méthode calcule la consommation des chaînes de production en fonction de leur niveau d'activation.
     * @return Cette méthode renvoie la quantité d'éléments consommée.
     */
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

    /**
     * Cette méthode vérifie que les chaînes de production peuvent être lancées en fonction de leur niveau d'activation et des quantités consommées.
     * @return Cette méthode renvoie true si la chaîne est réalisable, sinon elle renvoie false.
     */
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

    /**
     * Cette méthode calcule les éléments produits par les chaînes de production en fonction de leur niveau d'activation.
     * @return Cette méthode renvoie la liste des éléments produits avec la quantité produites correspondantes.
     */
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

    /**
     * Cette méthode calcule le bénéfice des chaînes de production en fonction de leur niveau d'activation.
     * @return Cette méthode renvoie le bénéfice.
     */
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

    /**
     * @return Cette méthode renvoie le stock de l'usine.
     */
    public Stockage getStockage() {
        return stockage;
    }

    /**
     * @return Cette méthode renvoie la liste d'achat.
     */
    public ListeAchat getListeAchat() {
        return listeAchat;
    }

    /**
     * @return Cette méthode renvoie le fichier contenant les employés.
     */
    public Fichier getEmployes() {
        return employes;
    }

    /**
     * @return Cette méthode renvoie la liste des employés.
     */
    public ArrayList<Employes> getListeEmployes(){
        return this.listEmployes;
    }

    /**
     * @return Cette méthode renvoie la liste des chaînes de production.
     */
    public ArrayList<ChaineProduction> getListeChaine() {
        return listeChaine;
    }

    /**
     * Cette méthode permet de remplacer la liste des chaînes de production.
     * @param listeChaine Correspond à la liste des chaînes de production qui doit remplacer la liste actuelle.
     */
    public void setListeChaine(ArrayList<ChaineProduction> listeChaine) {
        this.listeChaine = listeChaine;
    }

    /**
     * @return Cette méthode renvoie le nombre d'employés qualifiés dont l'usine a besoin.
     */
    public int getDemandeEQ() {
        return demandeEQ;
    }

    /**
     * Cette méthode permet de remplacer le nombre d'employés qualifiés dont l'usine a besoin.
     * @param demandeEQ Correspond au nombre d'employés qualifiés qui doit remplacer l'actuel.
     */
    public void setDemandeEQ(int demandeEQ) {
        this.demandeEQ = demandeEQ;
    }

    /**
     * @return Cette méthode renvoie le nombre d'employés non qualifiés dont l'usine a besoin.
     */
    public int getDemandeENQ() {
        return demandeENQ;
    }

    /**
     * Cette méthode permet de remplacer le nombre d'employés non qualifiés dont l'usine a besoin.
     * @param demandeENQ Correspond au nombre d'employés non qualifiés qui doit remplacer l'actuel.
     */
    public void setDemandeENQ(int demandeENQ) {
        this.demandeENQ = demandeENQ;
    }

    /**
     * @return Cette méthode renvoie le nombre d'employés qualifiés disponibles.
     */
    public int getOffreEQ() {
        return offreEQ;
    }

    /**
     * Cette méthode permet de remplacer le nombre d'employés qualifiés disponibles.
     * @param offreEQ Correspond au nombre d'employés qualifiés disponibles qui doit remplacer l'actuel.
     */
    public void setOffreEQ(int offreEQ) {
        this.offreEQ = offreEQ;
    }

    /**
     * @return Cette méthode renvoie le nombre d'employés non qualifiés disponibles.
     */
    public int getOffreENQ() {
        return offreENQ;
    }

    /**
     * Cette méthode permet de remplacer le nombre d'employés non qualifiés disponibles.
     * @param offreENQ Correspond au nombre d'employés non qualifiés disponibles qui doit remplacer l'actuel.
     */
    public void setOffreENQ(int offreENQ) {
        this.offreENQ = offreENQ;
    }

    /**
     * @return Cette méthode renvoie les informations de l'usine mises en forme.
     */
    @Override
    public String toString() {
        return this.stockage.toString();
    }

}
