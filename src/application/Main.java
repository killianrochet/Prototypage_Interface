package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root  = FXMLLoader.load(getClass().getResource("/Vue/FenetreChoix.fxml"));
			Scene scene = new Scene(root,1100,740);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Choix");
			primaryStage.setResizable(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}