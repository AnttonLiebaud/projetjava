package modele;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Usine {

    private ArrayList<ChaineProduction> listeChaine = new ArrayList<>();
    private Stockage stockage;
    private Fichier element;
    private Fichier chaines;
    private ArrayList<ChaineProduction> chaineProd;
    private ListeAchat listeAchat=new ListeAchat();

    public Usine(){
    }

    /**
     * Chargement des données des fichiers csv fournis par l'utilisateur
     * @param pathElements chemin d'accès (absolu) du fichier éléments
     * @param pathChaines chemin d'accès (absolu) du fichier chaine
     * @return un booleen indiquant si les fichiers ont bien été chargé
     */
    public boolean loadFichier(String pathElements, String pathChaines){
        this.element=new Fichier(pathElements);
        this.chaines=new Fichier(pathChaines);

        if(!(this.element.loadData()) || !(this.chaines.loadData())){
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
            chainesProd.add(new ChaineProduction(elem[0], elem[1], 0, elem[2], elem[3]));
        }
        this.chaineProd = chainesProd;
    }

    public Map<String, String> calculConso() {
        Map<String, String> dico = new HashMap<String, String>();
        for (int i = 0; i < this.chaineProd.size(); i++) {
            if (this.chaineProd.get(i).getNivActivation().getValue() > 0) {
                for (int j = 0; j < this.chaineProd.get(i).getElemProd().get(0).flux.length; j = j + 2) {
                    if (dico.containsKey(this.chaineProd.get(i).getElemProd().get(0).flux[j])) {
                        double temp1 = Double.parseDouble(this.chaineProd.get(i).getElemProd().get(0).flux[j +1]);
                        temp1 = temp1 * chaineProd.get(i).getNivActivation().getValue();
                        double temp2 = Double.parseDouble(dico.get(this.chaineProd.get(i).getElemProd().get(0).flux[j]).toString());
                        double temp3 = temp1 + temp2;
                        String temp = "" + temp3;
                        dico.replace(this.chaineProd.get(i).getElemProd().get(0).flux[j], temp);
                    } else {
                        dico.put(this.chaineProd.get(i).getElemProd().get(0).flux[j], this.chaineProd.get(i).getElemProd().get(0).flux[j + 1]);
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
                        String[] achat={stock.getStock().get(i).getCode().getValue(),String.valueOf(stock.getStock().get(i).getPrixAchat().getValue()*(consomme-stock.getStock().get(i).getQuantite().getValue()))};
                        this.listeAchat.setListe(achat);
                    }
                }
            }
        }
        return chainePossible;
    }

    public Map<String, String> calculProduction() {
        Map<String, String> dico = new HashMap<String, String>();
        for (int i = 0; i < this.chaineProd.size(); i++) {
            if (this.chaineProd.get(i).getNivActivation().getValue() > 0) {
                for (int j = 0; j < this.chaineProd.get(i).getElemProd().get(1).flux.length; j = j + 2) {
                    if (dico.containsKey(this.chaineProd.get(i).getElemProd().get(1).flux[j])) {
                        double temp1 = Double.parseDouble(this.chaineProd.get(i).getElemProd().get(1).flux[j +1]);
                        double temp2 = Double.parseDouble(dico.get(this.chaineProd.get(i).getElemProd().get(1).flux[j]).toString());
                        double temp3 = temp1 + temp2;
                        String temp = "" + temp3;
                        dico.replace(this.chaineProd.get(i).getElemProd().get(1).flux[j], temp);
                    } else {
                        dico.put(this.chaineProd.get(i).getElemProd().get(1).flux[j], this.chaineProd.get(i).getElemProd().get(1).flux[j + 1]);
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
                    revenu += (this.stockage.getStock().get(i).getQuantite().getValue()-consomme)*this.stockage.getStock().get(i).getPrixVente().getValue();
                }
                else{
                    revenu+=this.stockage.getStock().get(i).getQuantite().getValue()*this.stockage.getStock().get(i).getPrixVente().getValue();

                }
            }
        }

        ArrayList<String[]> listeAchat=this.listeAchat.getListe();
        for (int i = 0; i < listeAchat.size(); i++) {
            depense+=Double.parseDouble(listeAchat.get(i)[1]);
        }

        Map<String, String> dico = calculProduction();
        for (int i = 0; i < this.stockage.getStock().size(); i++) {
            if(dico.containsKey(this.stockage.getStock().get(i).getCode())){
                depense+=Double.parseDouble(dico.get(this.stockage.getStock().get(i).getCode()))*this.stockage.getStock().get(i).getPrixVente().getValue();
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

    @Override
    public String toString() {
        return this.stockage.toString();
    }

}
