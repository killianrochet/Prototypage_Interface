package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChoixController {
	  @FXML
	    void VoirProfil(ActionEvent event) {
		  
		  try {
				
				
			Parent root  = FXMLLoader.load(getClass().getResource("/Vue/Profil.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Profil");
			stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}

	    }

	    @FXML
	    void VoirSejours(ActionEvent event) {
	    	try {
	    		Parent root  = FXMLLoader.load(getClass().getResource("/Vue/Sejours.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.setTitle("Sejours");
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}

	    }


}