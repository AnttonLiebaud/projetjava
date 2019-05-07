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
import modele.Achat;
import modele.Elements;
import modele.ListeAchat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultatSimuOverviewController implements Initializable {

    private Stage dialogStage;
    private MainApp mainApp;
    private double benefice;
    private ObservableList<Achat> listAchat = FXCollections.observableArrayList();

    @FXML
    private Label beneficeLabel;

    @FXML
    private TableView<Achat> achatTable;
    @FXML
    private TableColumn<Achat, String> codeColumn;
    @FXML
    private TableColumn<Achat, String> nomColumn;
    @FXML
    private TableColumn<Achat, String> quantiteColumn;
    @FXML
    private TableColumn<Achat, String> coutcolumn;
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
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().numElementProperty());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomElementProperty());
        quantiteColumn.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty());
        coutcolumn.setCellValueFactory(cellData -> cellData.getValue().coutProperty());

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
        this.benefice = mainApp.getUsine().benefice();
        beneficeLabel.setText(benefice + " €");
        listAchat.addAll(mainApp.getUsine().getListeAchat().getAchat());
        achatTable.setItems(listAchat);

    }
}
