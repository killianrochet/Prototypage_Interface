package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.ResourceBundle;

import Connexion.dbConnexion;
import Model.Sejour;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SejourController implements Initializable {
	
	 	@FXML
	    private Button AfficherSejourbtn;

	    @FXML
	    private Button RechercherBtn;

	    @FXML
	    private Button RetourBtn;

	    
	    
	    
	    @FXML
	    private TableView<Sejour> table;
	    
	    @FXML
	    private TableColumn<Sejour, StringProperty> CompetancesColumn;

	    @FXML
	    private TableColumn<Sejour, StringProperty> DatesColumn;

	    @FXML
	    private TableColumn<Sejour, StringProperty> HoteColumn;

	    @FXML
	    private TableColumn<Sejour, IntegerProperty> NbrJoursColumn;

	    @FXML
	    private TableColumn<Sejour, IntegerProperty> NbrPersonnesRechercheesColumn;

	    @FXML
	    private TableColumn<Sejour, StringProperty> RestaurationColumn;
	    
	    @FXML
	    private TableColumn<Sejour, StringProperty> PhotoColumn;
	    
	    @FXML
	    private TableColumn<Sejour, StringProperty> ActionColumn;
	    
	    
	    //Les Trois options de recherche
	    @FXML
	    private Button CompeResearchBtn;
	    @FXML
	    private Button EmailResearchBtn;
	    @FXML
	    private Button HoteResearchBtn;

	    
	   
	    
	    
	  

      
	    
	    
	    @FXML
	    void ActionAfficherSejour(ActionEvent event) {
	    	
	    	
	    	
	    	
		    ObservableList<Sejour> data = FXCollections.observableArrayList() ;

	    	try {
	    	
	    		//Faire appel à ma class dbConnexion
	    		
				Connection conn = dbConnexion.Connect();
				String sql = "SELECT * FROM Sejours" ;
				
				PreparedStatement statement = conn.prepareStatement(sql) ;
				
			
		        ImageView PhotoProfilDefaut = new ImageView(new Image(this.getClass().getResourceAsStream("images/sejours/sejour02.jpg")));
				
				//Executer la requête SQL
				ResultSet rs = statement.executeQuery() ;
				while(rs.next()) {
					//Recuperation des données depuis la base de données
					data.add(new Sejour(rs.getString(1), "Personnes Recherchées :"+ rs.getInt(2), "Nmbr Jours :" + rs.getInt(3), "Food :" + rs.getString(4), rs.getString(5), rs.getString(6), PhotoProfilDefaut));
					
				}
				
				//Ajout dee valeurs récupéres dans les colonnes 
			
				//Set de la photo de sejour
				PhotoColumn.setCellFactory(col -> {
		            TableCell<Sejour, StringProperty> cell = new TableCell<>();
		            ImageView ImageSejour = new ImageView(new Image(this.getClass().getResourceAsStream("images/sejours/sejour02.jpg")));
		            
					
		            Sejour sejourExemple = new Sejour("Essaid", "3", "3", "Essaid", "Essaid","Essaid", ImageSejour);
		            Node graphic = createGraphicImage(sejourExemple.getPhotoColumn());
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphic));
		          
		           
		            return cell;
		        });
				
				
		    	//Set informations sur le profil Hote 
				HoteColumn.setCellFactory(col -> {
			            TableCell<Sejour, StringProperty> cell = new TableCell<>();
			            ImageView photoProfil = new ImageView(new Image(this.getClass().getResourceAsStream("images/profile_picture.png")));
			            
						//Set Hotename en creant un profile hote 
			            Sejour ProfilHote = new Sejour("Essaid", "3", "3", "Essaid", "Essaid","Essaid", photoProfil);
			            Node graphic = createDriverGraphic(ProfilHote.getHote());
	                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphic));
			          
			           
			            return cell;
			        });
				
				
		    	
				NbrPersonnesRechercheesColumn.setCellValueFactory(new PropertyValueFactory<Sejour, IntegerProperty>("NbrPersonnesRechercheesColumn"));
		    	//NbrPersonnesRechercheesColumn.setStyle( "-fx-alignment: CENTER;");
		    	NbrJoursColumn.setCellValueFactory(new PropertyValueFactory<Sejour, IntegerProperty>("NbrJoursColumn"));
		    	//NbrJoursColumn.setStyle( "-fx-alignment: CENTER;");
		    	CompetancesColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("CompetancesColumn"));
		    	
		    	RestaurationColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("RestaurationColumn"));
		    	
		    	DatesColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("DatesColumn"));
		    	
		    	ActionColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("button"));
		    
		    	//ActionColumn.setStyle( "-fx-background-color: pink; -fx-text-fill: green; -fx-font-size: 10px;");
		    	//ActionColumn.setStyle("-fx-text-fill: red ; -fx-background-color: red ; -fx-font: 22 arial; -fx-base: #b6e7c9;");
		    	
		    	
		    	table.getItems().addAll(data);
		    	

				//Fermer la connexion 
				conn.close(); 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	
	    


	    }

	    
	    //L'ajout d'une photo de Sejour 
	    private Node createGraphicImage(ImageView photoColumn) {
			// TODO Auto-generated method stub
	    	GridPane trackingDetailsHolder = new GridPane();
	         trackingDetailsHolder.setHgap(5);
	         ImageView driverPicture = photoColumn;
	         driverPicture.setPreserveRatio(true);
	         driverPicture.setFitHeight(80d);
	         GridPane.setRowSpan(driverPicture, 2);
	         trackingDetailsHolder.getChildren().add(driverPicture);

	         return trackingDetailsHolder;
		}
	    
	    //L'ajout du status d'un profil utilisateur et sa note 
		private Node createDriverGraphic(String newValue) {

	         GridPane trackingDetailsHolder = new GridPane();
	         trackingDetailsHolder.setHgap(5);
	         ImageView driverPicture = new ImageView(new Image(getClass().getResourceAsStream("images/profile_picture.png")));
	         driverPicture.setPreserveRatio(true);
	         driverPicture.setFitHeight(30d);
	         GridPane.setRowSpan(driverPicture, 2);
	         trackingDetailsHolder.getChildren().add(driverPicture);

	         Label driverStatus = new Label();
	         driverStatus.setText(newValue);
	         GridPane.setColumnIndex(driverStatus, 1);
	         trackingDetailsHolder.getChildren().add(driverStatus);

	         HBox rating = new HBox();
	         
	         int status = genererInt(0, 5);
	         for (int i = 0; i < status; i++) {
	 	        rating.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("images/star_yellow.png"))));

	         }

	         for (int i = 0; i < 5 - status; i++) {
	        	 rating.getChildren().add(
	                     new ImageView(new Image(getClass().getResourceAsStream("images/star_black.png")))
	             );
	         }
	        
	         GridPane.setConstraints(rating, 1, 1);
	         trackingDetailsHolder.getChildren().add(rating);

	         return trackingDetailsHolder;
		}
		
		
		private int genererInt(int borneInf, int borneSup){
			   Random random = new Random();
			   int nb;
			   nb = borneInf+random.nextInt(borneSup-borneInf);
			   return nb;
			}

	    
	    
	    @FXML
	    void ActionRechercherBtn(ActionEvent event) {
	    	System.out.println("vous avez cliquer sur le bouton Rechercher ");
	    	
	    	

	    }

	    @FXML
	    void ActionRetourBtn(ActionEvent event) {
	    	System.out.println("vous avez cliquer sur le bouton Retour");
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

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}

}
