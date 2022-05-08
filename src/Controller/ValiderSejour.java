package Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

@SuppressWarnings("unused")

public class ValiderSejour extends Application {

    public static void main(String[] args) {

        Application.launch(ValiderSejour.class,args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {   // Page valider séjour
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./View/ValiderSejour.fxml"));
            primaryStage.setTitle("Valider Sejour");
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}

