package vue;

import ctrl.MainApp;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import modele.Elements;
import modele.Employes;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeController implements Initializable {

    @FXML
    private TableView<Employes> EmployeTable;
    @FXML
    private TableColumn<Employes, String> codeColumn;
    @FXML
    private TableColumn<Employes, String> qualifieColumn;
    @FXML
    private TableColumn<Employes, Number> heureColumn;

    @FXML
    private Button bt_retour;




    // Reference to the main application.;
    public MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public EmployeController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the person table with the two columns.
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
        qualifieColumn.setCellValueFactory(cellData -> cellData.getValue().getQualifie());
        heureColumn.setCellValueFactory(cellData -> cellData.getValue().getNbHeure());

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

        EmployeTable.setItems(mainApp.getEmploye());

    }
}
