package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.ResourceBundle;

import Connexion.dbConnexion;
import Model.Sejour;
import Model.SejourDisco;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class SejourDiscoController {
	
	@FXML
    private Button AfficherSejourbtn;

    @FXML
    private Button RechercherBtn;

    @FXML
    private Button RetourBtn;
    
  

    
    
    
    @FXML
    private TableView<SejourDisco> table;
    
    @FXML
    private TableColumn<SejourDisco, StringProperty> CompetancesColumn;

    @FXML
    private TableColumn<SejourDisco, StringProperty> DatesColumn;

    @FXML
    private TableColumn<SejourDisco, StringProperty> HoteColumn;

    @FXML
    private TableColumn<SejourDisco, IntegerProperty> NbrJoursColumn;

    @FXML
    private TableColumn<SejourDisco, IntegerProperty> NbrPersonnesRechercheesColumn;

    @FXML
    private TableColumn<SejourDisco, StringProperty> RestaurationColumn;
    
    @FXML
    private TableColumn<SejourDisco, StringProperty> PhotoColumn;
    
    @FXML
    private TableColumn<SejourDisco, StringProperty> ActionColumn;
    
    
    //Les Trois options de recherche
    @FXML
    private Button CompeResearchBtn;
    @FXML
    private Button EmailResearchBtn;
    @FXML
    private Button HoteResearchBtn;
    
    @FXML
    private Button profil;
    
    @FXML
    private Button voyages;
    
    @FXML
    private Button deconnecter;
    
    
    @FXML
	private Button btnName;

	@FXML
	private Button btnEmail;

	@FXML
	private Button btnService;
	
	@FXML
	private TextField filterField;
	
	
	//Boutons Filtres
	Boolean btnEmail_visible = true;
	Boolean btnName_visible = true;
	Boolean btnService_visible = true;

	@FXML
	void btnEmailClick(ActionEvent event) {
		if (btnEmail_visible) {
			btnEmail.setStyle("-fx-background-color: #f3e5f5; ");
			btnEmail_visible = false;
		} else {
			btnEmail.setStyle("-fx-background-color: #000000; ");
			btnEmail_visible = true;
		}

	}

	@FXML
	void btnNameClick(ActionEvent event) {

		if (btnName_visible) {
			btnName.setStyle("-fx-background-color: #f3e5f5; ");
			btnName_visible = false;
		}

		else {
			btnName.setStyle("-fx-background-color: #000000; ");
			btnName_visible = true;
		}

	}

	@FXML
	void btnServiceClick(ActionEvent event) {

		if (btnService_visible) {
			btnService.setStyle("-fx-background-color: #f3e5f5; ");
			btnService_visible = false;
		} else {
			btnService.setStyle("-fx-background-color: #000000; ");
			btnService_visible = true;
		}

	}

    
    @FXML
    void deconnecterAction(ActionEvent event) {
    	try {
			
			
  			Parent root  = FXMLLoader.load(getClass().getResource("/Vue/DetailsSejours.fxml"));
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
    void profilAction(ActionEvent event) {
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
    void voyagesAction(ActionEvent event) {
    	 try {
				
				
  			Parent root  = FXMLLoader.load(getClass().getResource("/Vue/Sejours.fxml"));
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
    void ActionAfficherSejour(ActionEvent event) {
    	
	    ObservableList<SejourDisco> data = FXCollections.observableArrayList() ;
	    
	    Task taskBdd = new Task<Void>()  {
   		 @Override public Void call() throws Exception{
   			 Platform.runLater(() ->  {
   			 try {
   				//Faire appel à ma class dbConnexion
   		    		
   					Connection conn = dbConnexion.Connect();
   					String sql = "SELECT * FROM Sejours" ;
   					
   					PreparedStatement statement = conn.prepareStatement(sql) ;
   					System.out.println("Task Bdd Lancé ... ");
   					
   				
   			        ImageView PhotoProfilDefaut = new ImageView(new Image(this.getClass().getResourceAsStream("images/sejours/sejour02.jpg")));
   					
   					//Executer la requête SQL
   					ResultSet rs = statement.executeQuery() ;
   					while(rs.next()) {
   						//Récuperation des données depuis la base de données
   						data.add(new SejourDisco(rs.getString(1), "Personnes Recherchées :"+ rs.getInt(2), "Nmbr Jours :" + rs.getInt(3), "Food :" + rs.getString(4), rs.getString(5), rs.getString(6), PhotoProfilDefaut));
   						
   					}
   					//Fermer la connexion 
   					conn.close(); 
					
				} catch (Exception e) {
					// TODO: handle exception
				}

   			 });
   			 return null;
   	 }

   	};
   	new Thread(taskBdd).start();
	    
	    
	    

    	
	
	//Ajout dee valeurs récupéres dans les colonnes 

	//Set de la photo de SejourDisco
	PhotoColumn.setCellFactory(col -> {
        TableCell<SejourDisco, StringProperty> cell = new TableCell<>();
        ImageView ImageSejour = new ImageView(new Image(this.getClass().getResourceAsStream("images/sejours/sejour02.jpg")));
        
		
        SejourDisco sejourExemple = new SejourDisco("Essaid", "3", "3", "Essaid", "Essaid","Essaid", ImageSejour);
        Node graphic = createGraphicImage(sejourExemple.getPhotoColumn());
        cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphic));
      
       
        return cell;
    });
	
	
	//Set informations sur le profil Hote 
	HoteColumn.setCellFactory(col -> {
            TableCell<SejourDisco, StringProperty> cell = new TableCell<>();
            ImageView photoProfil = new ImageView(new Image(this.getClass().getResourceAsStream("images/profile_picture.png")));
            
			//Set Hotename en creant un profile hote 
            SejourDisco ProfilHote = new SejourDisco("Essaid", "3", "3", "Essaid", "Essaid","Essaid", photoProfil);
            Node graphic = createDriverGraphic(ProfilHote.getHote());
            cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphic));
          
           
            return cell;
        });
	
	
	
	NbrPersonnesRechercheesColumn.setCellValueFactory(new PropertyValueFactory<SejourDisco, IntegerProperty>("NbrPersonnesRechercheesColumn"));
	//NbrPersonnesRechercheesColumn.setStyle( "-fx-alignment: CENTER;");
	NbrJoursColumn.setCellValueFactory(new PropertyValueFactory<SejourDisco, IntegerProperty>("NbrJoursColumn"));
	//NbrJoursColumn.setStyle( "-fx-alignment: CENTER;");
	CompetancesColumn.setCellValueFactory(new PropertyValueFactory<SejourDisco,StringProperty>("CompetancesColumn"));
	
	RestaurationColumn.setCellValueFactory(new PropertyValueFactory<SejourDisco,StringProperty>("RestaurationColumn"));
	
	DatesColumn.setCellValueFactory(new PropertyValueFactory<SejourDisco,StringProperty>("DatesColumn"));
	
	ActionColumn.setCellValueFactory(new PropertyValueFactory<SejourDisco,StringProperty>("button"));

	
	
	table.getItems().addAll(data);
	

	
	
	//Fonctionnalité de research dans le tableau de sejours
	
	
	FilteredList<SejourDisco> listFiltred = new FilteredList<>(data, b -> true);
	filterField.textProperty().addListener((observable, oldInput, input) -> {
		listFiltred.setPredicate(sejour -> {
			if (input == null || input.isEmpty() || input.toString().length() == 1) {
				return true;
			}
			String toLowerCaseFilter = input.toLowerCase();
			System.out.println("Task Searching  Lancé ... ");

			if (sejour.getRestauration().toLowerCase().indexOf(toLowerCaseFilter) != -1 && btnName_visible) {
				return true;
			} else if (sejour.getCompetances().toLowerCase().indexOf(toLowerCaseFilter) != -1 && btnService_visible) {
				return true;
			} else if (sejour.getDates().toLowerCase().indexOf(toLowerCaseFilter) != -1 && btnEmail_visible) {
				return true;
			}
			else
				return false;
		});

	});

	SortedList<SejourDisco> output = new SortedList<>(listFiltred);
	output.comparatorProperty().bind(table.comparatorProperty());
	table.setItems(output);
		
    	
    


    }

    
    //L'ajout d'une photo de SejourDisco 
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

   

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		/*
		 *  // Create connexion to db
        Connection conn = dbConnexion.Connect();
        String sql = "INSERT INTO Sejours (Hote, NbrPersonnes, NbrJours, Restauration, Competences, Dates) values (?, ?, ?, ?, ?, ?)" ;
        
          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = conn.prepareStatement(sql);
         
          preparedStmt.setInt(2, 1);
          preparedStmt.setInt(3, 2);
          preparedStmt.setString(4, "matin - soir");
          preparedStmt.setString(5, "Cuisine");
          preparedStmt.setString(6, "01/02/2022 - 03/02/2022");
              
        //Executer la requête SQL 10.000 fois
        for(int i = 0; i < 10000; i++)
        {
            preparedStmt.setString (1, "Michel" + (i+1));
            preparedStmt.execute();
            System.out.println("Vous avez ajouté " + (i+1) + " entrée à la BDD");
              
        }
            
        //Fermer la connexion 
        conn.close(); 
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
		 */
	}

}
