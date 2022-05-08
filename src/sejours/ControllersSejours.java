package sejours;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ControllersSejours implements Initializable {

    @FXML
    private Text personne;

    @FXML
    private Text nbJours;

    @FXML
    private Text restauration;

    @FXML
    private Text dates;

    @FXML
    private Text description;

    @FXML
    private Text nomHote;

    @FXML
    private ImageView imageSejour;

    @FXML
    private Text nomSejour;
     
    public void translateAnimation(double duration, Node node, double witdh) {
    	
    	TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
    	translateTransition.setByX(witdh);
    	translateTransition.play();
    }

    int show = 0;
    List<String> myList = new ArrayList<String>();
    Sejours a = new Sejours(myList);
    Random r = new Random();

    @FXML
    void backPicture(ActionEvent event) {
    	int n = r.nextInt(myList.size());
    	imageSejour.setImage(new Image(myList.get(n)));  	
    }

    @FXML
    void nextPicture(ActionEvent event) {  	
    	int n = r.nextInt(myList.size());
    	imageSejour.setImage(new Image(myList.get(n)));  
    }

    @FXML
    void startConversation(ActionEvent event) {
    	String s = "Salut";
    	
    	nomHote.setText(s);

    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	    List<String> myList = new ArrayList<String>();
	    myList.add("C:\\Users\\Killian\\Desktop\\images\\exterieurMaison.jpg");
	    myList.add("C:\\Users\\Killian\\Desktop\\images\\ImageMaison.jpg");
	    myList.add("C:\\Users\\Killian\\Desktop\\images\\interieurMaison.jpg");
		
		nomSejour.setText("Palavas les flots");
		nbJours.setText("7 jours");
		personne.setText("3 personnes");
		restauration.setText("Matin - Midi");
		dates.setText("01/03/2022 - 07/03/2022");


		nomHote.setText("Killian");
		description.setText("Si tu ne comprends pas, ou ne maîtrises pas, la notion de coefficient binomial, inutile de chercher à calculer toi-même les nombres de Catalan, que tu découvris dans cette obscure revue américaine d'algèbre, croyant qu'il s'agissait de “nombres catalans” (l'anglais Catalan numbers est équivoque), avant de faire le chemin historique et de découvrir qu'ils auraient tout aussi bien pu se nommer suite d'Euler, entiers de Seger, ou nombres de Ming Antu.");
		
		
	}

}
