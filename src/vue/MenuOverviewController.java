package vue;

import ctrl.MainApp;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuOverviewController implements Initializable {


    private MainApp mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
