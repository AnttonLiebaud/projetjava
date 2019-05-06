package vue;

import ctrl.MainApp;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modele.ChaineProduction;
import modele.Elements;
import modele.Flux;

import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

public class ChaineProdOverviewController implements Initializable {

    private ObservableList<Elements> ElemEntreeData = FXCollections.observableArrayList();
    private ObservableList<Elements> ElemSortieData = FXCollections.observableArrayList();
    @FXML
    private TableView<ChaineProduction> chaineTable;
    @FXML
    private TableColumn<ChaineProduction, String> codeColumn;
    @FXML
    private TableColumn<ChaineProduction, String> nomColumn;

    @FXML
    private TableView<Elements> ElemSortieTable;
    @FXML
    private TableColumn<Elements, String> codeSortieColumn;
    @FXML
    private  TableColumn<Number,Number> quantiteSortieColumn;


    @FXML
    private TableView<Elements> ElemEntreeTable;
    @FXML
    private TableColumn<Elements, String> codeEntreeColumn;
    @FXML
    private  TableColumn<Number,Number> quantiteEntreeColumn;


    @FXML
    private Button bt_retour;



    // Reference to the main application.;
    public MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ChaineProdOverviewController() {
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

        codeEntreeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
        //quantiteEntreeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());

        codeSortieColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
        //quantiteEntreeColumn.setCellValueFactory(cellData -> cellData.getValue().getNom());

        showChaineDetails(null);
        chaineTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showChaineDetails(newValue));

        bt_retour.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainApp.showMenuOverview();
            }
        });




    }
    private void showChaineDetails(ChaineProduction newChaine) {
        ElemEntreeTable.getItems().clear();
        ElemSortieTable.getItems().clear();

        ObservableList<Flux> o = FXCollections.observableArrayList();
/*
        if (newChaine != null) {
            int i = 0;
            for(String s : newChaine.getElemProd().get(0).flux ){
                if(i % 2 == 0){
                    for (Elements e : mainApp.getStockData()){
                        if(e.getCode().getValue().equals(s)){
                            ElemEntreeData.add(e);
                        }
                    }
                }else{


                }
                i++;
            }
            ElemEntreeTable.setItems(ElemEntreeData);
                i = 0;
            for(String s : newChaine.getElemProd().get(1).flux ){
                if(i % 2 == 0){
                    for (Elements e : mainApp.getStockData()){
                        if(e.getCode().getValue().equals(s)){
                            ElemSortieData.add(e);
                        }
                    }
                }else{

                }
                i++;
            }

            ElemSortieTable.setItems(ElemSortieData);

        }*/

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
