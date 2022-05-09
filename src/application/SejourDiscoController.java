package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Connexion.dbConnexion;
import Model.Sejour;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.beans.binding.Bindings;

import java.util.*;

public class SejourDiscoController implements Initializable {
	
	 	@FXML
	    private Button AfficherSejourbtn;

	    @FXML
	    private Button RechercherBtn;

	    @FXML
	    private Button GenerateBtn;
	    
	    @FXML
	    private TableView<Sejour> table;
	    
	    @FXML
	    private TableColumn<Sejour, StringProperty> CompetencesColumn;

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
	    void ActionAfficherSejour(ActionEvent event) {
	    	long start = System.currentTimeMillis();
	    	
		    ObservableList<Sejour> data = FXCollections.observableArrayList() ;

	    	try {
	    		// Create connexion to db
				Connection conn = dbConnexion.Connect();
				String sql = "SELECT * FROM Sejours" ;
				PreparedStatement statement = conn.prepareStatement(sql);
				//Execute Query
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					data.add(new Sejour(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6)));
				}
				// Adding every column to cells values
		    	//HoteColumn.setCellValueFactory(new PropertyValueFactory<Sejour, StringProperty>("HoteColumn"));
				
		    	HoteColumn.setCellFactory(col -> {
		            TableCell<Sejour, StringProperty> cell = new TableCell<>();
		            
//		            Node graphic = createDriverGraphic();
//                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphic));

//		            cell.itemProperty().addListener((observableValue, o, newValue) -> {
//		            	System.out.println("coucou 1");
//		                if (newValue != null) {
//		                	System.out.println("coucou 2");
//		                    Node graphic = createDriverGraphic(newValue);
//		                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphic));
//		                }
//		            });
		            return cell;
		        });
		    	
		    	NbrPersonnesRechercheesColumn.setCellValueFactory(new PropertyValueFactory<Sejour, IntegerProperty>("NbrPersonnesRechercheesColumn"));
		    	NbrJoursColumn.setCellValueFactory(new PropertyValueFactory<Sejour, IntegerProperty>("NbrJoursColumn"));
		    	CompetencesColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("CompetencesColumn"));
		    	RestaurationColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("RestaurationColumn"));
		    	DatesColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("DatesColumn"));
		    	
		    	
		    	// Retrieve rows in list
		    	//table.getItems().addAll(data);
		    	//List<Sejour> sejours = data;
		    	table.setItems(FXCollections.observableList(data));
		    	//table.getStyleClass().add("noheader");
		    	
				// Close connexion
				conn.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	long end = System.currentTimeMillis();
	    	// Calculate execution time (around 700ms for 10.000 entries)
	    	System.out.println("Temps d'affichage : " + (end-start) + "ms");
	    }
	    
	    private Node createDriverGraphic(StringProperty hote){

	        GridPane trackingDetailsHolder = new GridPane();
	        trackingDetailsHolder.setHgap(5);
	        ImageView picture = new ImageView(new Image("https://www.maisons-open.fr/wp-content/uploads/2021/06/20210630-maisons-open-home.jpg"));
	        picture.setPreserveRatio(true);
	        picture.setFitHeight(30d);
	        GridPane.setRowSpan(picture, 2);
	        trackingDetailsHolder.getChildren().add(picture);

	        Label status = new Label();
	        status.setText("status");
	        GridPane.setColumnIndex(status, 1);
	        trackingDetailsHolder.getChildren().add(status);

//			HBox rating = new HBox();
//	        for (int i = 0; i < driver.getRating(); i++) {
//	            rating.getChildren().add(
//	                    new ImageView(new Image(getClass().getResourceAsStream("https://www.maisons-open.fr/wp-content/uploads/2021/06/20210630-maisons-open-home.jpg")))
//	            );
//	        }
//
//	        for (int i = 0; i < (5 - driver.getRating()); i++) {
//	            rating.getChildren().add(
//	                    new ImageView(new Image(getClass().getResourceAsStream("https://www.maisons-open.fr/wp-content/uploads/2021/06/20210630-maisons-open-home.jpg")))
//	            );
//	        }
//	        GridPane.setConstraints(rating, 1, 1);
//	        trackingDetailsHolder.getChildren().add(rating);

	        return trackingDetailsHolder;
	    }

	    @FXML
	    void ActionRechercherBtn(ActionEvent event) {
	    	System.out.println("Vous avez cliqué sur le bouton Rechercher ");
	    }

	    @FXML
	    void ActionGenerateBtn(ActionEvent event) {
	    	try {
	    		// Create connexion to db
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
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub	
		}
}