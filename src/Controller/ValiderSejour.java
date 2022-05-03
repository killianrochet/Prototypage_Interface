package Controller;

        import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import java.io.IOException;

@SuppressWarnings("unused")

public class ValiderSejour extends Application {

    public static void main(String[] args) {

        Application.launch(ValiderSejour.class,args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {   // Page principale
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./View/ValiderSejour.fxml"));
        primaryStage.setTitle("Valider Sejour");
        primaryStage.setScene(new Scene(root,600,550));
        primaryStage.show();

    }


}