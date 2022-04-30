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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SejourController implements Initializable {
	
	 	@FXML
	    private Button AfficherSejourbtn;

	    @FXML
	    private Button RechercherBtn;
	    
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
	    	
		    ObservableList<Sejour> data = FXCollections.observableArrayList() ;

	    	try {
	    		// Create connexion to db
				Connection conn = dbConnexion.Connect();
				String sql = "SELECT * FROM Sejours" ;
				PreparedStatement statement = conn.prepareStatement(sql);
				//Execute Query
				ResultSet rs = statement.executeQuery() ;
				while(rs.next()) {
					data.add(new Sejour(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6)));
					
				}
				// Adding every column to cells values
		    	HoteColumn.setCellValueFactory(new PropertyValueFactory<Sejour, StringProperty>("HoteColumn"));
		    	NbrPersonnesRechercheesColumn.setCellValueFactory(new PropertyValueFactory<Sejour, IntegerProperty>("NbrPersonnesRechercheesColumn"));
		    	NbrJoursColumn.setCellValueFactory(new PropertyValueFactory<Sejour, IntegerProperty>("NbrJoursColumn"));
		    	CompetencesColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("CompetencesColumn"));
		    	RestaurationColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("RestaurationColumn"));
		    	DatesColumn.setCellValueFactory(new PropertyValueFactory<Sejour,StringProperty>("DatesColumn"));
		    	
		    	// Retrieve rows in list
		    	table.getItems().addAll(data);
		    	
				// Close connexion
				conn.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void ActionRechercherBtn(ActionEvent event) {
	    	System.out.println("Vous avez cliqué sur le bouton Rechercher ");
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub	
		}
}