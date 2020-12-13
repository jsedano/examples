package dev.jsedano.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class App extends Application {

    public static void main( String[] args ){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        File f = new File("src/main/resources/exampleui.fxml");
        Parent root  = FXMLLoader.load(f.toURI().toURL());

        stage.setScene(new Scene(root));
        stage.show();
    }
}
