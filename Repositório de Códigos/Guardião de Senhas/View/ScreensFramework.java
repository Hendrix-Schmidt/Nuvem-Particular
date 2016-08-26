/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

//2.534

import java.lang.reflect.UndeclaredThrowableException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Schmidt
 */
public class ScreensFramework extends Application {

    public static String screen1ID = "Guardião de Senhas_BETA  v.1.0.0.BETA";
    public static String screen1File = "ViewPrincipal.fxml";
    public static String screen2ID = "Ajude-me";
    public static String screen2File = "ViewAjuda.fxml";
    public static String screen3ID = "Sugestoes";
    public static String screen3File = "ViewWebView.fxml"; 
    
    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);

        mainContainer.setScreenFade(ScreensFramework.screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setMaxHeight(540);
        primaryStage.setMaxWidth(600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle(screen1ID);
        primaryStage.getIcons().add(new Image("file:C:/Users/Schmidt/Documents"
                + "/NetBeansProjects/GuardiaoDeSenhas/src/Images"
                + "/Hendrix-logo-cor-correta.png"));
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
