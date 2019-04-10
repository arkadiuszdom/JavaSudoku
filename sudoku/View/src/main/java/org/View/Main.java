package org.View;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;


public class Main extends Application {
    
    
    @Override
    public void start(final Stage primaryStage) {
        try {
           // ResourceBundle resourceBundle = ResourceBundle.getBundle("org.View", locale);
//          Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"), resourceBundle);
            
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/Game.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
           

            
            
            primaryStage.setScene(scene);
            primaryStage.show();
                      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        launch(args);
    }
}
