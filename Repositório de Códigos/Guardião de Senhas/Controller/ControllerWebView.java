/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ScreensController;
import View.ScreensFramework;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Schmidt
 */
public class ControllerWebView implements Initializable, ControlledScreen {

    ScreensController myController;
    WebEngine engine;
    //String link = "https://hendrixschmidt.wufoo.com/forms/z1js5ldf1v68fuw/";
    public static final String DEFAULT_URL = "https://hendrixschmidt.wufoo.com/forms/z1js5ldf1v68fuw/";

    @FXML
    private WebView webView;

    @FXML
    private Button buttonVoltar;

    @FXML
    void buttonVoltar(ActionEvent event) {
        myController.setScreenNoTransition(ScreensFramework.screen1ID);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
            final WebEngine webEngine = webView.getEngine();
            webEngine.load(DEFAULT_URL);
       
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}
