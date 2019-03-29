package vue;

import ctrl.MainApp;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import modele.Elements;
import modele.Usine;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StockOverviewController implements Initializable {
        @FXML
        private TableView<Elements> stockTable;
        @FXML
        private TableColumn<Elements, String> codeColumn;
        @FXML
        private TableColumn<Elements, String> nomColumn;
        @FXML
        private TableColumn<Elements, Number> quantiteColumn;
        @FXML
        private TableColumn<Elements, String> uniteColumn;

        @FXML
        private Button bt_retour;




        // Reference to the main application.;
        public MainApp mainApp;

    /**
         * The constructor.
         * The constructor is called before the initialize() method.
         */
        public StockOverviewController() {
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
            quantiteColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantite());
            uniteColumn.setCellValueFactory(cellData -> cellData.getValue().getUnite());

            bt_retour.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mainApp.showMenuOverview();
                }
            });


        }

        /**
         * Is called by the main application to give a reference back to itself.
         *
         * @param mainApp
         */
        public void setMainApp(MainApp mainApp) {
            this.mainApp = mainApp;

            stockTable.setItems(mainApp.getStockData());

        }




}
