package ctrl;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.ChaineProduction;
import modele.Elements;
import modele.Stockage;
import modele.Usine;
import vue.*;

import java.io.IOException;
import java.util.ArrayList;


public class MainApp extends Application {


    private Stage primaryStage;
    private BorderPane rootLayout;
    private Usine usine = new Usine();
    private ObservableList<Elements> stockData = FXCollections.observableArrayList();
    private ObservableList<ChaineProduction> chaineData = FXCollections.observableArrayList();

    public MainApp(){
        this.loadData();
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SimulationPro");

        initRootLayout();



        showMenuOverview();

    }

    public void loadData(){
        this.usine.loadFichier("/home/cortes/IdeaProjects/projetjava/src/data/elements.csv","/home/cortes/IdeaProjects/projetjava/src/data/chaines.csv", "/home/cortes/IdeaProjects/projetjava/src/data/employes.csv");
        this.usine.creationStockage();
        this.usine.creationChaines();
        this.usine.creationEmployes();
        ArrayList<Elements> stock = usine.getStockage().getStock();
        ArrayList<ChaineProduction> chaineProd = usine.getChaineProd();
        stockData.clear();
        chaineData.clear();

        for (Elements e: stock) {
            stockData.add(e);
        }

        for (ChaineProduction e: chaineProd) {
            chaineData.add(e);
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../vue/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showMenuOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../vue/MenuOverview.fxml"));
            AnchorPane mainOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainOverview);

            MenuOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStockOverview(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../vue/StockOverview.fxml"));
            AnchorPane stockOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(stockOverview);

            StockOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChaineOverview(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../vue/ChaineProdOverview.fxml"));
            AnchorPane chaineOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(chaineOverview);

            ChaineProdOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSimulationOverview(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../vue/SimulationOverview.fxml"));
            AnchorPane SimulationOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(SimulationOverview);

            SimulationOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showResumeDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../vue/ResultatSimuOverview.fxml"));
            AnchorPane ResultatSimuOverview = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Resultat Simulation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(ResultatSimuOverview);
            dialogStage.setScene(scene);


            // Set the person into the controller.
            ResultatSimuOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane getRootLayout (){
        return rootLayout;
    }

    public Usine getUsine(){
        return usine;
    }

    public ObservableList<Elements> getStockData() {
        return stockData;
    }

    public ObservableList<ChaineProduction> getChaineData() {
        return chaineData;
    }
}