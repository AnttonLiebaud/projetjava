package vue;

import ctrl.MainApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import modele.ChaineProduction;
import java.net.URL;
import java.util.*;

public class SimulationOverviewController implements Initializable {
    @FXML
    private TableView<ChaineProduction> chaineTable;
    @FXML
    private TableColumn<ChaineProduction, String> codeColumn;
    @FXML
    private TableColumn<ChaineProduction, String> nomColumn;
    @FXML
    private TableColumn<ChaineProduction, Number> nivColumn;
    @FXML
    private Button bt_retour;
    @FXML
    private Button bt_save;
    @FXML
    private Button bt_simulation;
    @FXML
    private ImageView icone_warning;

    @FXML
    private Text text_alert;

    @FXML
    private Label codeLabel;
    @FXML
    private Label nommLabel;
    @FXML
    private Label label_code;
    @FXML
    private Label label_nom;
    @FXML
    private Label label_niv;

    @FXML
    private TextField nivActivationTextField;

    Map<String, Integer> bloquant = new HashMap<>();





    // Reference to the main application.;
    public MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public SimulationOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the person table with the two columns.
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().getNom());
        nivColumn.setCellValueFactory(cellData -> cellData.getValue().getNivActivation());

        showChaineDetails(null);
        chaineTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showChaineDetails(newValue));

        bt_retour.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainApp.showMenuOverview();
            }
        });

        bt_save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ChaineProduction selectedChaine = chaineTable.getSelectionModel().getSelectedItem();
                    selectedChaine.setNivActivation(Integer.parseInt(nivActivationTextField.getText()));
                }catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("ERREUR");
                    alert.setHeaderText("Valeur entrée erreur");
                    alert.setContentText("La valeur entrée doit être un entier");

                    alert.showAndWait();
                }
                mainApp.getUsine().getListeAchat().getAchat().clear();
                mainApp.getUsine().getListeAchat().setCoutTotal(0);
                mainApp.getUsine().creationStockage();
                mainApp.getUsine().setDemandeENQ(0);
                mainApp.getUsine().setDemandeEQ(0);
                mainApp.getUsine().setOffreENQ(0);
                mainApp.getUsine().setOffreEQ(0);


                if(!mainApp.getUsine().verificationChaine()){
                    ChaineProduction selectedChaine = chaineTable.getSelectionModel().getSelectedItem();
                    boolean newBloquant = true;
                    for(int i = 0; i < bloquant.size(); i++){
                        if(bloquant.containsKey(selectedChaine.getNom().getValue())){
                            newBloquant = false;
                        }
                    }
                    if(newBloquant) {
                        bloquant.put(selectedChaine.getNom().getValue(), selectedChaine.getNivActivation().getValue());
                    }
                    else{
                        bloquant.replace(selectedChaine.getNom().getValue(), selectedChaine.getNivActivation().getValue());
                    }
                    icone_warning.setVisible(true);
                    text_alert.setVisible(true);
                    text_alert.setText("ATTENTION ! Tout ou partie des ces niveaux d'activation sont bloquant !");
                    String nom = null;
                    int niv = 0;
                    Iterator i = bloquant.keySet().iterator();

                    while (i.hasNext()) {
                        nom = (String) i.next();
                        niv = (Integer) bloquant.get(nom);
                        text_alert.setText(text_alert.getText() + "\nChaine : " + nom + " \nValeur d'activation : " + niv + "\n");


                    }
                }
                else{
                    text_alert.setVisible(false);
                    icone_warning.setVisible(false);
                    bloquant.clear();
                }


            }
        });

        bt_simulation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainApp.getUsine().getListeAchat().getAchat().clear();
                mainApp.getUsine().getListeAchat().setCoutTotal(0);
                mainApp.getUsine().creationStockage();
                mainApp.getUsine().setDemandeENQ(0);
                mainApp.getUsine().setDemandeEQ(0);
                mainApp.getUsine().setOffreENQ(0);
                mainApp.getUsine().setOffreEQ(0);
                boolean allinit = true;
                for(ChaineProduction c : chaineTable.getItems() ){
                    if(c.getNivActivation() != null){


                    }
                    else{
                        allinit = false;
                    }

                }
                if(allinit && mainApp.getUsine().verificationChaine()){
                    mainApp.showResumeDialog();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("SOLUTION IMPOSSIBLE");
                    alert.setHeaderText("ERREUR");
                    alert.setContentText("Des produits ne sont pas disponible dans \n le stock, ni à l'achat");

                    alert.showAndWait();

                }
            }
        });






    }

    private void showChaineDetails(ChaineProduction newChaine){
        if(newChaine != null){
            codeLabel.setText(newChaine.getCode().getValue());
            nommLabel.setText(newChaine.getNom().getValue());
            label_code.setVisible(true);
            label_nom.setVisible(true);
            nivActivationTextField.setVisible(true);
            bt_save.setVisible(true);
            label_niv.setVisible(true);
        }
        else{
            codeLabel.setText("");
            nommLabel.setText("");
            bt_save.setVisible(false);
            nivActivationTextField.setVisible(false);
            label_code.setVisible(false);
            label_nom.setVisible(false);
            label_niv.setVisible(false);
        }

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        chaineTable.getItems().clear();
        chaineTable.setItems(mainApp.getChaineData());

    }
}
