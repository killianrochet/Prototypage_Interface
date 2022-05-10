package application;

import java.awt.font.NumericShaper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



import javax.imageio.ImageIO;

import Model.Utilisateur;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProfilController implements Initializable {
	@FXML
    private TextField adresse;
	@FXML
    private TextField date_naissance;

    @FXML
    private TextField email;

    @FXML
    private TextField nom;

    @FXML
    private TextField num_tel;

    @FXML
    private TextField prenom;
	
	@FXML
    private Button ChangerPhoto;

    @FXML
    private Button EnregistrerModifs;

    @FXML
    private ImageView Photo;
    
    @FXML
    private Button btnRetour;
    
    private FileChooser fileChooser ;
    private File filePath;
    
  
    Utilisateur Utilisateur = new Utilisateur("MEGHELLET", "ESSAID", "12/07/1998", "SAID1998@outlouk.com", "0769893745", "160 rue de la république,84000 avignon ");
    
   

    @FXML
    void ActionEnregistrerModifs(ActionEvent event) {
    	Utilisateur.setNom(nom.getText());
    	Utilisateur.setPrenom(prenom.getText());
    	Utilisateur.setDate_naissance(date_naissance.getText());
    	Utilisateur.setEmail(email.getText());
    	Utilisateur.setAdresse(adresse.getText());
    	Utilisateur.setNumeroTel(num_tel.getText());

    }
    
    @FXML
    void ActionRetourVuePrecedente(ActionEvent event) {
    	 try {
				Parent root  = FXMLLoader.load(getClass().getResource("/Vue/FenetreChoix.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Choix");
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}

    }

    /*
     * Cette methode permet de changer la photo de profile d'un utiliseur
     */
    
    public void chooseImageButtonPushed(ActionEvent event)
    {
    	 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	 fileChooser = new FileChooser();
    	 
    	 //Set the user's directory
    	 String userDirectoryString = System.getProperty("user.home") + "/Documents/Ceri/M1_S2/Prototype_interface/Prototypage_JavaFx/images";
    	 File userDirectory = new File(userDirectoryString);
    	 
    	 if(!userDirectory.canRead())
    		 userDirectory = new File("/home");
    	 fileChooser.setInitialDirectory(userDirectory);
    	 
    	 this.filePath = fileChooser.showOpenDialog(stage);
    	 
    	 try {
    		 BufferedImage bufferedImage = ImageIO.read(filePath);
    		 Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    		 
    		 Photo.setImage(image);
    		 
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
    	 
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		nom.setText(Utilisateur.getNom());
		prenom.setText(Utilisateur.getPrenom());
		date_naissance.setText(Utilisateur.getDate_naissance());
		num_tel.setText(Utilisateur.getNumeroTel());
		email.setText(Utilisateur.getEmail());
		adresse.setText(Utilisateur.getAdresse());
	}

	
    
    
    
    
    
   
    
    
   
}