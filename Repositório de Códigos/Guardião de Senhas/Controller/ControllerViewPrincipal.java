/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ScreensController;
import Model.ModelItem;
import Model.ModelUsuario;
import View.AlertBox;
import View.ScreensFramework;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Schmidt
 */
public class ControllerViewPrincipal implements Initializable, ControlledScreen {

    ScreensController myController;
    ModelUsuario mu = new ModelUsuario();
    ControllerUsuario cu = new ControllerUsuario();
    ControllerItem controllerItem = new ControllerItem();
    Crypt crypt = new Crypt();

    public ControllerViewPrincipal() {
    }
    @FXML
    private AnchorPane anchorPai;
//Variaveis do painel LOGIN
    @FXML
    private AnchorPane anchorLogin;
    @FXML
    private Button buttonCliqueAqui, ButtonCancelarLogin, buttonEntrarLogin;
    @FXML
    private TextField textLoginLog;
    @FXML
    private PasswordField passSenhaLogin;
//------------------------------------------------------------------------------    
//Variaveis do painel CADUSER
    @FXML
    private AnchorPane anchorCadUser;
    @FXML
    private Button buttonCadUser, buttonCancelarUser;
    @FXML
    private TextField textNome, textSobrenome, textUsuario;
    @FXML
    private PasswordField passSenha, passSenhaConfirm;
//------------------------------------------------------------------------------    
//Variaveis do painel PRINCIPAL   
    @FXML
    private AnchorPane anchorPrincipal;
    @FXML
    private Button buttonSairPrincipal;
    @FXML
    private Label labelNomeusuario;
//------------------------------------------------------------------------------    
//Variaveis do painel CONSINTER
    @FXML
    private AnchorPane anchorConsInter;
    @FXML
    private ImageView imgWebView, imgConsSimples, imgConsLivre;
    @FXML
    private Label labelConsSimples, labelConsLivre;
    @FXML
    private Button buttonVoltarConsInter;
    //------------------------------------------------------------------------------
//Variaveis do painel CADINTER
    @FXML
    private AnchorPane anchorCadInter;
    @FXML
    private ImageView imgCadastroItem, imgCadastroCateg;
    @FXML
    private Label labelCadItem, labelCadCateg;
    @FXML
    private Button buttonVoltarCadInter;
//------------------------------------------------------------------------------
//Variaveis do painel REMOVERITEM
    @FXML
    private AnchorPane anchorRemoverItem;
    @FXML
    private Button buttonRemoverRemov, buttonCancRemov;
    @FXML
    private Label labelRemItem, labelRemSenha, labelRemObs, labelRemCat, labelRemId;
    @FXML
    private ComboBox<String> comboRemItem;
//------------------------------------------------------------------------------
//Variaveis do painel ALTERARITEM
    @FXML
    private AnchorPane anchorAlterarItem;
    @FXML
    private Button buttonSalvarAlt, buttonCancAlt;
    @FXML
    private Label labelAltItemId;
    @FXML
    private TextField textAltSenha, textAltObs, textAltItem;
    @FXML
    private ComboBox<String> comboAltItens, comboAltCat;
//------------------------------------------------------------------------------
//Variaveis do painel CONSULTA
    @FXML
    private AnchorPane anchorConsulta;
    @FXML
    private Button buttonVoltarCons;
    @FXML
    private TableView<String> tableConsItem;
    @FXML
    private TableColumn<ModelItem, String> tableColId;
    @FXML
    private TableColumn<ModelItem, String> tableColCategoria;
    @FXML
    private TableColumn<ModelItem, String> tableColUsuario;
    @FXML
    private TableColumn<ModelItem, String> tableColSenhas;
    @FXML
    private TableColumn<ModelItem, String> tableColObserv;
//------------------------------------------------------------------------------
//Variaveis do painel CONSULTA Avançada
    @FXML
    private AnchorPane anchorConsultaAvancada;
    @FXML
    private TextField textBusca;
    @FXML
    private Button buttonPesquisar, buttonVoltarConsAvan;
    @FXML
    private TableView<String> tableConsAvanc;
    @FXML
    private TableColumn<ModelItem, String> columId;
    @FXML
    private TableColumn<ModelItem, String> columCat;
    @FXML
    private TableColumn<ModelItem, String> columUsuario;
    @FXML
    private TableColumn<ModelItem, String> columSenha;
    @FXML
    private TableColumn<ModelItem, String> columObs;
//------------------------------------------------------------------------------    
//Variaveis do painel CADITEM
    @FXML
    private AnchorPane anchorCadItem;
    @FXML
    private Button buttonSalvarCadItem, buttonSairCadItem;
    @FXML
    private TextField textNomeItemCad, textSenhaItemCad, textObsItemCad;
    @FXML
    private ComboBox<String> comboCategoriaItemCad;
    ObservableList<String> list = FXCollections.observableArrayList(
            "Hotmail", "Gmail", "Outlook", "Yahoo", "Email", "Faculdade", "Forum",
            "Games", "Site", "Pessoal", "Rede Solcial", "Trabalho", "Mais Usados",
            "Antigos", "Aplicativos", "Banco Online", "Governo", "Outros"
    );
//------------------------------------------------------------------------------    
//Variaveis do painel CONFIGURAÇÕES
    @FXML
    private AnchorPane anchorConfig;

    @FXML
    private Button buttonVoltarConfig;
//------------------------------------------------------------------------------
    //Variaveis do painel Excluir Usuario.
    @FXML
    private AnchorPane anchorExcUser;

//------------------------------------------------------------------------------
//Variaveis do IMAGEVIEW
    @FXML
    private ImageView imgCadItem, imgConsItem, imgRemovItem, imgAltItem, imgAjuda, imgConfig;
    @FXML
    private Label labelAjuda, labelSugest, labelConf;
//------------------------------------------------------------------------------
//Eventos do painel LOGIN COMAÇAM AQUI

    @FXML
    void buttonCliqueAqui(ActionEvent event) {
        anchorLogin.setVisible(false);
        anchorCadUser.setVisible(true);
    }

    @FXML
    void buttonEntrarLogin(ActionEvent event) throws BadPaddingException,
            NoSuchPaddingException, IllegalBlockSizeException,
            InvalidKeyException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        mu.setUsuario(textLoginLog.getText().trim());
        mu.setSenha(crypt.encrypt(passSenhaLogin.getText().trim(), passSenhaLogin.getText().trim()));
        if (cu.checkAuthenticationModel(mu) == true) {
            anchorLogin.setVisible(false);
            anchorPrincipal.setVisible(true);

            translate(600, 0, 0, 0, 500, imgCadItem);
            translate(700, 0, 0, 0, 700, imgConsItem);
            translate(800, 0, 0, 0, 900, imgRemovItem);
            translate(900, 0, 0, 0, 1100, imgAltItem);

            translate(0, 200, 0, 0, 500, imgAjuda);
            translate(0, 200, 0, 0, 900, imgWebView);
            translate(0, 200, 0, 0, 1100, imgConfig);
            translate(0, 150, 0, 0, 700, labelAjuda);
            translate(0, 150, 0, 0, 1000, labelSugest);
            translate(0, 150, 0, 0, 1200, labelConf);
            System.out.println(mu.getId());
            mu.setNome(cu.getNome(mu));
            labelNomeusuario.setText(" " + mu.getNome() + "!");
        }
        textLoginLog.clear();
        passSenhaLogin.clear();
    }

    @FXML
    void ButtonCancelarLogin(ActionEvent event) {
        System.exit(0);
    }
//------------------------------------------------------------------------------    
//Eventos do painel CADUSER COMAÇAM AQUI  

    @FXML
    void buttonCadUser(ActionEvent event) throws BadPaddingException,
            NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        if (textNome.getText().trim().isEmpty()) {

            AlertBox.display("ERRO", "\n  O campo NOME está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textNome);
        } else if (textSobrenome.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo SOBRENOME está em "
                    + "branco.\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textSobrenome);
        } else if (textUsuario.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo USUÁRIO está em "
                    + "branco.\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textUsuario);
        } else if (passSenha.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo SENHA está em "
                    + "branco.\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, passSenha);
        } else if (passSenhaConfirm.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo CONFIRMAÇÂO DE SENHA está em "
                    + "branco.\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, passSenhaConfirm);
        } else if (!passSenha.getText().equals(passSenhaConfirm.getText())) {
            AlertBox.display("ERRO", "\n  As senhas não conferem. "
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, passSenha);
            vibration(10, 0, 5, 50, passSenhaConfirm);
        } else if ((passSenha.getText().equals(passSenhaConfirm.getText()))
                && !(textNome.getText().trim().isEmpty()
                && textSobrenome.getText().trim().isEmpty()
                && textUsuario.getText().trim().isEmpty())) {
            mu.setNome(textNome.getText().trim());
            mu.setSobrenome(textSobrenome.getText().trim());
            mu.setUsuario(textUsuario.getText().trim());
            mu.setSenha(crypt.encrypt(passSenha.getText().trim(), passSenha.getText().trim()));
            if (cu.getIdLoginByLogin(mu) == false) {
                cu.inserirModelUsuario(mu);
            } else {
                AlertBox.display("ERRO", "\n\tUsuario " + mu.getUsuario() + " Ja existe :(\n\n\t"
                        + "Tentecolocar " + mu.getUsuario() + "2 .\n\n\tOu coloque "
                        + "outro de sua preferencia!");
            }
            textNome.clear();
            textSobrenome.clear();
            textUsuario.clear();
            passSenha.clear();
            passSenhaConfirm.clear();
        }
    }

    @FXML
    void buttonCancelarUser(ActionEvent event) {
        anchorCadUser.setVisible(false);
        anchorLogin.setVisible(true);
        textNome.clear();
        textSobrenome.clear();
        textUsuario.clear();
        passSenha.clear();
        passSenhaConfirm.clear();
    }
//------------------------------------------------------------------------------ 
//Eventos do painel PRINCIPAL COMAÇAM AQUI 

    @FXML
    void imgAltItem(MouseEvent event) {
        anchorPrincipal.setVisible(false);
        anchorAlterarItem.setVisible(true);
        ObservableList<String> list2 = FXCollections.observableArrayList();
        List colecaoAlt = controllerItem.getId(mu);
        String aux;
        for (int i = 0; i < colecaoAlt.size(); i++) {
            int j = i;
            aux = colecaoAlt.get(j).toString();//.replace("[^A-Za-z]", "");
            list2.add(aux);
            System.out.printf("Posição %d - %s comparado com AUX : ''" + aux + "'';\n", i, colecaoAlt.get(i));
        }
        comboAltItens.getItems().addAll(list2);
    }

    @FXML
    void imgCadItem(MouseEvent event) {
        anchorPrincipal.setVisible(false);
        anchorCadInter.setVisible(true);
        translate(0, -400, 0, 0, 400, imgCadastroItem);
        translate(0, 400, 0, 0, 400, imgCadastroCateg);
        translate(-400, 0, 0, 0, 400, labelCadItem);
        translate(400, 0, 0, 0, 400, labelCadCateg);
    }

    @FXML
    void imgConsItem(MouseEvent event) {
        anchorPrincipal.setVisible(false);
        anchorConsInter.setVisible(true);
        translate(0, -400, 0, 0, 400, imgConsSimples);
        translate(0, 400, 0, 0, 400, imgConsLivre);
        translate(-400, 0, 0, 0, 400, labelConsSimples);
        translate(400, 0, 0, 0, 400, labelConsLivre);

    }

    @FXML
    void imgRemovItem(MouseEvent event) {
        ControllerItem ci = new ControllerItem();
        anchorPrincipal.setVisible(false);
        anchorRemoverItem.setVisible(true);
        ObservableList<String> list1 = FXCollections.observableArrayList();
        List colecaoAlt = ci.getId(mu);
        String aux;
        for (int i = 0; i < colecaoAlt.size(); i++) {
            int j = i;
            aux = colecaoAlt.get(j).toString();//.replace("[^A-Za-z]", "");
            list1.add(aux);
            System.out.printf("Posição %d - %s comparado com AUX : ''" + aux + "'';\n", i, colecaoAlt.get(i));
        }
        comboRemItem.getItems().addAll(list1);
        event.consume();
    }

    @FXML
    void imgAjuda(MouseEvent event) {
        myController.setScreenNoTransition(ScreensFramework.screen2ID);

    }

    @FXML
    void imgWebView(MouseEvent event) {
        myController.setScreenNoTransition(ScreensFramework.screen3ID);
    }

    @FXML
    void imgConfig(MouseEvent event) {
        anchorPrincipal.setVisible(false);
        anchorConfig.setVisible(true);
    }

    @FXML
    void buttonSairPrincipal(ActionEvent event) {
        boolean escolha = AlertBox.displayOption("Aviso", "Tem certeza que deseja sair do sistema? :(");

        if (escolha == true) {
            System.exit(0);
        }
    }
//------------------------------------------------------------------------------ 
//Eventos do painel CONSINTER COMAÇAM AQUI 

    @FXML
    void buttonVoltarConsInter(ActionEvent event) {
        anchorConsInter.setVisible(false);
        anchorPrincipal.setVisible(true);
        translate(600, 0, 0, 0, 500, imgCadItem);
        translate(700, 0, 0, 0, 700, imgConsItem);
        translate(800, 0, 0, 0, 900, imgRemovItem);
        translate(900, 0, 0, 0, 1100, imgAltItem);

        translate(0, 200, 0, 0, 500, imgAjuda);
        translate(0, 200, 0, 0, 900, imgWebView);
        translate(0, 200, 0, 0, 1100, imgConfig);
        translate(0, 150, 0, 0, 700, labelAjuda);
        translate(0, 150, 0, 0, 1000, labelSugest);
        translate(0, 150, 0, 0, 1200, labelConf);
    }

    @FXML
    void imgConsLivre(MouseEvent event) {
        anchorConsInter.setVisible(false);
        anchorConsultaAvancada.setVisible(true);

    }

    @FXML
    void imgConsSimples(MouseEvent event) {// esse metodo preenche a tabela da consulta simples
        anchorConsInter.setVisible(false);
        anchorConsulta.setVisible(true);
        List colecaoTab = null;
        try {
            colecaoTab = controllerItem.getAll(mu);
            ObservableList<String> listTableColA = FXCollections.observableArrayList(colecaoTab);
            PropertyValueFactory<ModelItem, String> cellId = new PropertyValueFactory<>("id");
            PropertyValueFactory<ModelItem, String> cellCategoria = new PropertyValueFactory<>("categoria");
            PropertyValueFactory<ModelItem, String> cellItem = new PropertyValueFactory<>("item");
            PropertyValueFactory<ModelItem, String> cellSenha = new PropertyValueFactory<>("senha");
            PropertyValueFactory<ModelItem, String> cellObs = new PropertyValueFactory<>("observacao");

            tableColId.setCellValueFactory(cellId);
            tableColCategoria.setCellValueFactory(cellCategoria);
            tableColUsuario.setCellValueFactory(cellItem);
            tableColSenhas.setCellValueFactory(cellSenha);
            tableColObserv.setCellValueFactory(cellObs);
            tableConsItem.setItems(listTableColA);

        } catch (BadPaddingException | NoSuchPaddingException |
                IllegalBlockSizeException | InvalidKeyException |
                NoSuchAlgorithmException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(ControllerViewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//------------------------------------------------------------------------------
//Eventos do painel CADINTER COMAÇAM AQUI 

    @FXML
    void imgCadastroItem(MouseEvent event) {
        anchorCadInter.setVisible(false);
        anchorCadItem.setVisible(true);
    }

    @FXML
    void imgCadastroCateg(MouseEvent event) {

    }

    @FXML
    void buttonVoltarCadInter(ActionEvent event) {
        anchorCadInter.setVisible(false);
        anchorPrincipal.setVisible(true);

        translate(600, 0, 0, 0, 500, imgCadItem);
        translate(700, 0, 0, 0, 700, imgConsItem);
        translate(800, 0, 0, 0, 900, imgRemovItem);
        translate(900, 0, 0, 0, 1100, imgAltItem);

        translate(0, 200, 0, 0, 500, imgAjuda);
        translate(0, 200, 0, 0, 900, imgWebView);
        translate(0, 200, 0, 0, 1100, imgConfig);
        translate(0, 150, 0, 0, 700, labelAjuda);
        translate(0, 150, 0, 0, 1000, labelSugest);
        translate(0, 150, 0, 0, 1200, labelConf);
    }
//------------------------------------------------------------------------------
//Eventos do painel CADITEM COMAÇAM AQUI 

    @FXML
    void buttonSalvarCadItem(ActionEvent event) {
        //myController.setScreenNoTransition(ScreensFramework.screen2ID);
        if (comboCategoriaItemCad.getValue() == null) {
            AlertBox.display("ERRO", "\n  O Menu Lista está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, comboCategoriaItemCad);
        } else if (textNomeItemCad.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo ITEM está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textNomeItemCad);
        } else if (textSenhaItemCad.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo SENHA está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textSenhaItemCad);
        } else if (textObsItemCad.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo OBSERVAÇÃO está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textObsItemCad);
        } else if (!(comboCategoriaItemCad.getValue().trim().isEmpty() && textNomeItemCad.getText().trim().isEmpty()
                && textSenhaItemCad.getText().trim().isEmpty() && textObsItemCad.getText().trim().isEmpty())) {
            ModelItem mi = new ModelItem();
            ControllerItem ci = new ControllerItem();
            mi.setCategoria(comboCategoriaItemCad.getValue());
            mi.setItem(textNomeItemCad.getText().trim());
            mi.setSenha(textSenhaItemCad.getText().trim());
            mi.setObservacao(textObsItemCad.getText().trim());
            mi.setUsuario(mu.getId());
            ci.inserirItens(mi);
            textSenhaItemCad.clear();
            textObsItemCad.clear();
            textNomeItemCad.clear();
        }
    }

    @FXML
    void buttonSairCadItem(ActionEvent event) {
        anchorCadItem.setVisible(false);
        anchorCadInter.setVisible(true);
        comboCategoriaItemCad.getSelectionModel().clearSelection();
        textSenhaItemCad.clear();
        textObsItemCad.clear();
        textNomeItemCad.clear();
    }
//------------------------------------------------------------------------------
//Eventos do painel CONSULTA COMAÇAM AQUI 

    @FXML
    void buttonVoltarCons(ActionEvent event) {
        anchorConsulta.setVisible(false);
        anchorConsInter.setVisible(true);
    }
//------------------------------------------------------------------------------
//Eventos do painel CONSULTA Avançada COMAÇAM AQUI 

    @FXML
    void buttonPesquisar(ActionEvent event) {

        String query = textBusca.getText().replace("[^A-Z]", "").trim();
        List colecaoTab = null;
        if (query.isEmpty()) {
            AlertBox.display("AVISO", "\tO campo busca não contem nada.\n"
                    + "\tPor favor digite alguma palavra\n   ou número.");
            vibration(10, 0, 5, 50, textBusca);
        } else {
            try {
                colecaoTab = controllerItem.getItemFromLike(mu, query);
                ObservableList<String> listTableColA = FXCollections.observableArrayList(colecaoTab);
                PropertyValueFactory<ModelItem, String> cellId = new PropertyValueFactory<>("id");
                PropertyValueFactory<ModelItem, String> cellCategoria = new PropertyValueFactory<>("categoria");
                PropertyValueFactory<ModelItem, String> cellItem = new PropertyValueFactory<>("item");
                PropertyValueFactory<ModelItem, String> cellSenha = new PropertyValueFactory<>("senha");
                PropertyValueFactory<ModelItem, String> cellObs = new PropertyValueFactory<>("observacao");
                columId.setCellValueFactory(cellId);
                columCat.setCellValueFactory(cellCategoria);
                columUsuario.setCellValueFactory(cellItem);
                columSenha.setCellValueFactory(cellSenha);
                columObs.setCellValueFactory(cellObs);
                tableConsAvanc.setItems(listTableColA);
            } catch (BadPaddingException | NoSuchPaddingException |
                    IllegalBlockSizeException | InvalidKeyException |
                    NoSuchAlgorithmException | InvalidAlgorithmParameterException ex) {
                Logger.getLogger(ControllerViewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void buttonVoltarConsAvan(ActionEvent event) {
        anchorConsultaAvancada.setVisible(false);
        anchorConsInter.setVisible(true);
        tableConsAvanc.getItems().clear();
        textBusca.clear();
    }
//------------------------------------------------------------------------------    
//Eventos do painel REMOVERITEM COMAÇAM AQUI 

    @FXML
    void comboRemItem(ActionEvent event) throws BadPaddingException,
            NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        String j = comboRemItem.getValue();
        List<ModelItem> colecaoAlt = controllerItem.listarItemPorLinha(mu, j);
        labelRemId.setText(String.valueOf(colecaoAlt.get(0).getId()));
        labelRemCat.setText(colecaoAlt.get(0).getCategoria());
        labelRemItem.setText(colecaoAlt.get(0).getItem());
        labelRemSenha.setText(colecaoAlt.get(0).getSenha());
        labelRemObs.setText(colecaoAlt.get(0).getObservacao());
        event.consume();
    }

    @FXML
    void buttonRemoverRemov(ActionEvent event) {
        ModelItem mi = new ModelItem();
        ControllerItem ci = new ControllerItem();
        mi.setId(Integer.parseInt(comboRemItem.getValue()));
        mi.setItem(labelRemItem.getText().trim());
        ci.deletarItem(mi, mu);
        event.consume();
        labelRemId.setText("  ---");
        labelRemCat.setText("  ---");
        labelRemItem.setText("  ---");
        labelRemSenha.setText("  ---");
        labelRemObs.setText("  ---");
    }

    @FXML
    void buttonCancRemov(ActionEvent event) {
        comboRemItem.getSelectionModel().clearSelection();
        comboRemItem.getItems().clear();
        labelRemId.setText("  ---");
        labelRemCat.setText("  ---");
        labelRemItem.setText("  ---");
        labelRemSenha.setText("  ---");
        labelRemObs.setText("  ---");
        anchorRemoverItem.setVisible(false);
        anchorPrincipal.setVisible(true);
        event.consume();
    }
//------------------------------------------------------------------------------
//Eventos do painel ALTERARITEM COMAÇAM AQUI 

    @FXML
    void comboAltItens(ActionEvent event) {
        try {
            String j = comboAltItens.getValue();
            List<ModelItem> colecaoAlt = controllerItem.listarItemPorLinha(mu, j);
            System.out.printf("Valor de j = %s && Combo %s\n", j, comboAltItens.getValue());
            labelAltItemId.setText(j);
            comboAltCat.setValue(colecaoAlt.get(0).getCategoria());
            textAltItem.setText(colecaoAlt.get(0).getItem());
            textAltSenha.setText(colecaoAlt.get(0).getSenha());
            textAltObs.setText(colecaoAlt.get(0).getObservacao());
        } catch (BadPaddingException | NoSuchPaddingException |
                IllegalBlockSizeException | InvalidKeyException |
                NoSuchAlgorithmException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(ControllerViewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void buttonSalvarAlt(ActionEvent event) {
        if (comboAltCat.getValue() == null) {
            AlertBox.display("ERRO", "\n  O Menu Lista está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, comboAltCat);
        } else if (textAltItem.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo NOME está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textAltItem);
        } else if (textAltSenha.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo SENHA está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textAltSenha);
        } else if (textAltObs.getText().trim().isEmpty()) {
            AlertBox.display("ERRO", "\n  O campo OBSERVAÇÂO está em branco."
                    + "\n  Verifique e tente novamente.\n");
            vibration(10, 0, 5, 50, textAltObs);
        } else if (!(comboAltCat.getValue() == null && textAltItem.getText().trim().isEmpty()
                && textAltSenha.getText().trim().isEmpty() && textAltObs.getText().trim().isEmpty())) {
            ModelItem mi = new ModelItem();
            ControllerItem ci = new ControllerItem();
            mi.setId(Integer.parseInt(comboAltItens.getValue()));
            mi.setItem(textAltItem.getText().trim());
            mi.setCategoria(comboAltCat.getValue());
            mi.setSenha(textAltSenha.getText().trim());
            mi.setObservacao(textAltObs.getText().trim());
            ci.AtualizaItens(mi, mu);
            labelAltItemId.setText(" -");
            textAltItem.clear();
            textAltSenha.clear();
            textAltObs.clear();
        }
    }

    @FXML
    void buttonCancAlt(ActionEvent event) {
        anchorAlterarItem.setVisible(false);
        anchorPrincipal.setVisible(true);
        comboAltItens.getSelectionModel().clearSelection();
        comboAltItens.getItems().clear();
        comboAltCat.getSelectionModel().clearSelection();
        textAltSenha.clear();
        textAltObs.clear();
    }
//------------------------------------------------------------------------------
//Eventos do painel CONFIGURAÇÕES

    @FXML
    void buttonVoltarConfig(ActionEvent event) {
        anchorConfig.setVisible(false);
        anchorPrincipal.setVisible(true);
    }

    @FXML
    void imgExcluirUsuario(MouseEvent event) {
        anchorConfig.setVisible(false);
        anchorExcUser.setVisible(true);

    }

    @FXML
    void imgAlterarTema(MouseEvent event) {

    }
//------------------------------------------------------------------------------
//Eventos do painel Excluir usuario

    @FXML
    void buttonExUser(ActionEvent event) {
        boolean escolha = AlertBox.displayOption("Aviso", "Tem certeza que quer excluir seu usuario ?");

        if (escolha == true) {
            cu.deletarUsuario(mu);
            System.exit(0);
        }
    }

    @FXML
    void buttonVoltarExUser(ActionEvent event) {

        anchorExcUser.setVisible(false);
        anchorConfig.setVisible(true);

    }
//------------------------------------------------------------------------------

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboCategoriaItemCad.setItems(list);
        comboAltCat.setItems(list);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
//------------------------------------------------------------------------------

    public void translate(int fromX, int fromY, int toX, int toY, double duration, Node node) {
        TranslateTransition translateTransitionAj = new TranslateTransition();
        translateTransitionAj.setFromX(fromX);
        translateTransitionAj.setToX(toX);
        translateTransitionAj.setFromY(fromY);
        translateTransitionAj.setToY(toY);
        translateTransitionAj.setDuration(Duration.millis(duration));
        translateTransitionAj.setNode(node);
        translateTransitionAj.play();
    }

    public void vibration(int fromX1, int toX1, int cont, double duration, Node node) {
        TranslateTransition translateTransitionAj = new TranslateTransition();
        double fromX2 = fromX1 - (fromX1 * 2);

        translateTransitionAj.setFromX(fromX1);
        translateTransitionAj.setToX(toX1);
        translateTransitionAj.setFromX(fromX2);
        translateTransitionAj.setToX(toX1);
        translateTransitionAj.setCycleCount(cont);
        translateTransitionAj.cycleDurationProperty().getValue().lessThan(Duration.ONE);
        translateTransitionAj.setDuration(Duration.millis(duration));
        translateTransitionAj.setNode(node);
        translateTransitionAj.play();
    }

    public void simpleFade(int fromValue, int toValue, double duration, Node node) {
        FadeTransition fadeTransitionCad = new FadeTransition();
        fadeTransitionCad.setFromValue(fromValue);
        fadeTransitionCad.setToValue(toValue);
        fadeTransitionCad.setDuration(Duration.millis(duration));
        fadeTransitionCad.setNode(node);
        fadeTransitionCad.play();
    }

}
