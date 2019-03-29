package vue;

import ctrl.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Elements;
import modele.ListeAchat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultatSimuOverviewController implements Initializable {

    private Stage dialogStage;
    private MainApp mainApp;
    private double benefice;
    private ObservableList<String[]> listAchat = FXCollections.<String[]>observableArrayList();

    @FXML
    private Label beneficeLabel;

    @FXML
    private TableView<String[]> achatTable;
    @FXML
    private TableColumn<String, String> codeColumn;
    @FXML
    private TableColumn<String, String> quantiteColumn;
    @FXML
    private Button bt_retour;
    @FXML
    private Button bt_save;

    public ResultatSimuOverviewController(){

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        bt_retour.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dialogStage.close();
            }
        });

        bt_save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("ERREUR");
                alert.setHeaderText("Problème génération document");
                alert.setContentText("Erreur durant la génération du document");

                alert.showAndWait();
            }
        });

    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public  void showResultatDetail(){

    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        listAchat.addAll(mainApp.getUsine().getListeAchat().getListe());
        achatTable.setItems(listAchat);
        this.benefice = mainApp.getUsine().benefice();
        beneficeLabel.setText(benefice + " €");

    }
}
