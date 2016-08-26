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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author Schmidt
 */
public class ControllerViewAjuda implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    private Text textTexto;
    
    @FXML
    private ImageView imgAjudaCad, imgAjudaCons, imgAjudaRem, imgAjudaEdit,
            imgAjudaFunci;

    @FXML
    private AnchorPane anchorDireita, anchorAjudaCad, anchorAjudaCons,
            anchorAjudaRem, anchorAjudaEdit, anchorAjudaFunci, anchorEsquerda;

    @FXML
    void imgAjudaCad(MouseEvent event) {
        anchorAjudaCad.setVisible(true);
        textTexto.setVisible(false);
        anchorAjudaCons.setVisible(false);
        anchorAjudaEdit.setVisible(false);
        anchorAjudaFunci.setVisible(false);
        anchorAjudaRem.setVisible(false);
    }

    @FXML
    void imgAjudaCons(MouseEvent event) {
        textTexto.setVisible(false);
        anchorAjudaCons.setVisible(true);
        anchorAjudaCad.setVisible(false);
        anchorAjudaEdit.setVisible(false);
        anchorAjudaFunci.setVisible(false);
        anchorAjudaRem.setVisible(false);
    }

    @FXML
    void imgAjudaEdit(MouseEvent event) {
        textTexto.setVisible(false);
        anchorAjudaEdit.setVisible(true);
        anchorAjudaCons.setVisible(false);
        anchorAjudaCad.setVisible(false);
        anchorAjudaFunci.setVisible(false);
        anchorAjudaRem.setVisible(false);
    }

    @FXML
    void imgAjudaFunci(MouseEvent event) {
        textTexto.setVisible(false);
        anchorAjudaFunci.setVisible(true);
        anchorAjudaCons.setVisible(false);
        anchorAjudaCad.setVisible(false);
        anchorAjudaEdit.setVisible(false);
        anchorAjudaRem.setVisible(false);
    }

    @FXML
    void imgAjudaRem(MouseEvent event) {
        textTexto.setVisible(false);
        anchorAjudaRem.setVisible(true);
        anchorAjudaCons.setVisible(false);
        anchorAjudaCad.setVisible(false);
        anchorAjudaEdit.setVisible(false);
        anchorAjudaFunci.setVisible(false);

    }
        @FXML
    void butonVoltar(ActionEvent event) {
        myController.setScreenNoTransition(ScreensFramework.screen1ID);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
