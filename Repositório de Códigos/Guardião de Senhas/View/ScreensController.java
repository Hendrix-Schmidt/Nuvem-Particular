package View;

import Controller.ControlledScreen;
import javafx.scene.shape.Rectangle;
import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Angie
 */
public class ScreensController extends StackPane {
    //Holds the screens to be displayed

    private final HashMap<String, Node> screens = new HashMap<>();
    ScaleTransition scaleTransition;
    public ScreensController() {
        super();
    }

    //Add the screen to the collection
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    //Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return screens.get(name);
    }

// Carrega o arquivo fxml , adicione a tela para a coleta de telas e
    // Finalmente injeta o screenPane ao controlador.
    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenControler = ((ControlledScreen) myLoader.getController());
            myScreenControler.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Este método tenta apresentado o ecrã com um nome pré-definido.
    // Primeiro ela deixa claro que a tela já foi carregado. Em seguida, se houver mais do que
    // Uma tela a nova tela é foi adicionado segundo, e então a tela atual é removido.
    // Se não houver qualquer tela que está sendo exibido , a nova tela é apenas adicionado à raiz .
    public boolean setScreenFade(final String name) {
        if (screens.get(name) != null) {   //screen loaded
            final DoubleProperty opacity = opacityProperty();
            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade;
                fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);                    //remove the displayed screen
                                getChildren().add(0, screens.get(name));     //add the screen
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(800), new KeyValue(opacity, 2.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }
    }

    public boolean setScreenNoTransition(final String name) {
        if (screens.get(name) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);                    //remove the displayed screen
                getChildren().add(0, screens.get(name));
            } else {
                getChildren().add(screens.get(name));
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }

    }
    

    public boolean setScreenScale(final String name) {
        if (screens.get(name) != null) {   //screen loaded
            Rectangle rect = new Rectangle();
            if (!getChildren().isEmpty()) {    //if there is more than one screen
                rect.setArcHeight(15);
                rect.setArcWidth(15);
                rect.setFill(Color.TRANSPARENT);
                getChildren().add(rect);
                scaleTransition = ScaleTransitionBuilder.create()
                        .node(rect.getParent())
                        .duration(Duration.millis(800))
                        .toX(2)
                        .toY(2)
                        .cycleCount(2)
                        .autoReverse(true)
                        .build();
                scaleTransition.play();
            } else {
                getChildren().add(rect);
                getChildren().add(0, screens.get(name));
                scaleTransition = ScaleTransitionBuilder.create()
                        .node(rect.getParent())
                        .duration(Duration.millis(800))
                        .toX(2)
                        .toY(2)
                        .cycleCount(2)
                        .autoReverse(true)
                        .build();
                scaleTransition.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }
    }

    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
}
