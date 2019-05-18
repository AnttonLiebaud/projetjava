package vue;

import ctrl.MainApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
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
