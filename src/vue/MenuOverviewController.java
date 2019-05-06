package vue;

import ctrl.MainApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modele.Elements;
import sun.applet.Main;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuOverviewController implements Initializable {

    @FXML
    private Button bt_stock;
    @FXML
    private Button bt_load;
    @FXML
    private Button bt_chaine;
    @FXML
    private Button bt_employes;
    @FXML
    private Button bt_simu;
    @FXML
    private Label confirmeLabel;

    private MainApp mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmeLabel.setVisible(false);
        bt_stock.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainApp.showStockOverview();
            }
        });

        bt_load.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText("Confirmation refresh");
                alert.setContentText("Les modifications non enregistrées seront perdu");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    mainApp.loadData();
                    confirmeLabel.setVisible(true);
                }

            }
        });

        bt_chaine.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mainApp.showChaineOverview();

            }
        });

        bt_employes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mainApp.showEmployeOverview();

            }
        });

        bt_simu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mainApp.showSimulationOverview();

            }
        });
    }



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
