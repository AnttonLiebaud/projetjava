package vue;

import ctrl.MainApp;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem stockMenuItem;
    @FXML
    private MenuItem chaineMenuItem;
    @FXML
    private MenuItem employeMenuItem;
    @FXML
    private MenuItem simuMenuItem;
    @FXML
    private MenuItem chargerMenuItem;
    @FXML
    private MenuItem quitterMenuItem;
    @FXML
    private MenuItem menuMenuItem;


    private MainApp mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chargerMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText("Confirmation refresh");
                alert.setContentText("Les modifications non enregistrées seront perdu");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    mainApp.loadData();
                }
            }
        });
        employeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainApp.showEmployeOverview();
            }
        });

        stockMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainApp.showStockOverview();
            }
        });

        chaineMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainApp.showChaineOverview();
            }
        });

        simuMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainApp.showSimulationOverview();
            }
        });
        menuMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainApp.showMenuOverview();
            }
        });
        quitterMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
                System.out.println("active");
            }
        });

    }
    @FXML
    public void clickableMenu(){
        System.out.println("Menu clicked");
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
