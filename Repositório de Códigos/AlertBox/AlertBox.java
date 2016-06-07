/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Schmidt
 */
import javafx.event.ActionEvent;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class AlertBox {

    public static double initX = 300;
    public static double initY = 100;
    public static  boolean check;
    
    public static void display(String title, String message) {
        Stage window = new Stage();
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        window.setMinWidth(initX);
        window.setMinHeight(initY);

        Label label = new Label();
        label.setText(" " + message + "\t ");
        Button closeButton = new Button(" OK ");
        closeButton.setOnAction(e -> window.close());

        VBox painelPrincipal = new VBox(10);
        
        
        VBox layoutV = new VBox(10);
        layoutV.getChildren().add(label);
        layoutV.setAlignment(Pos.CENTER);
        
        HBox layoutH = new HBox(10);
        //layoutH.getStyleClass().add("borda3");
        layoutH.getChildren().add(closeButton);
        layoutH.setAlignment(Pos.BOTTOM_CENTER);
        
        StackPane stackH = new StackPane();
        //stackH.getStyleClass().add("borda");
        stackH.getChildren().add(layoutH);
        stackH.setAlignment(Pos.BOTTOM_CENTER);
        
        painelPrincipal.getChildren().addAll(layoutV, layoutH);
        painelPrincipal.setAlignment(Pos.CENTER);
        
        
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(painelPrincipal, initX, initY);
        scene.getStylesheets().add("Css/CssAlertBox.css");
        window.setScene(scene);
        
        window.showAndWait();
        window.toFront();

    }

    public static boolean displayOption(String title, String message) {
        
         
        
        Stage window = new Stage();
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        window.setMinWidth(250);
        //window.setMinHeight(250);
       
        Label label = new Label();
        label.setText(" " + message + "\t ");
        Button closeButton = new Button(" SAIR ");
        Button yesButton = new Button(" SIM ");
        Button noButton = new Button(" NÃO ");
        closeButton.setOnAction(e -> window.close());
        yesButton.setOnAction((ActionEvent e) -> {
            check = true;
            window.close();
        });
        noButton.setOnAction((ActionEvent e) -> {
            check = false;
            window.close();
        });
        
        VBox painelPrincipal = new VBox(10);

        VBox layoutV = new VBox(10);
        layoutV.getChildren().addAll(label);
        layoutV.setAlignment(Pos.CENTER);

        HBox layoutH = new HBox(10);
        layoutH.getChildren().addAll(yesButton, noButton, closeButton);
        layoutH.setAlignment(Pos.BOTTOM_CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().add(layoutH);
        stack.setAlignment(Pos.BOTTOM_CENTER);

        painelPrincipal.getChildren().addAll(layoutV, stack);
        painelPrincipal.setAlignment(Pos.CENTER);

        Scene scene = new Scene(painelPrincipal, initX, initY);
        scene.getStylesheets().add("Css/CssAlertBox.css");
        window.setScene(scene);
        window.showAndWait();
        
        return check;
    }

    public static boolean status (boolean bol){
        return bol;
    }
}
