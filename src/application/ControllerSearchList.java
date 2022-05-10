package application;

import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ControllerSearchList implements Initializable {

	@FXML
	private Label label;
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Client> tableview;
	@FXML
	private TableColumn<Client, String> ID;
	@FXML
	private TableColumn<Client, String> Name;
	@FXML
	private TableColumn<Client, String> Email;
	@FXML
	private TableColumn<Client, String> service;
	@FXML
	private TableColumn<Client, String> other;
	@FXML
	private Button btnName;

	@FXML
	private Button btnEmail;

	@FXML
	private Button btnService;
	
	

	Boolean btnEmail_visible = true;
	Boolean btnName_visible = true;
	Boolean btnService_visible = true;

	@FXML
	void btnEmailClick(ActionEvent event) {
		if (btnEmail_visible) {
			btnEmail.setStyle("-fx-background-color: #f3e5f5; ");
			btnEmail_visible = false;
		} else {
			btnEmail.setStyle("-fx-background-color: #ff6090; ");
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
			btnName.setStyle("-fx-background-color: #ff6090; ");
			btnName_visible = true;
		}

	}

	@FXML
	void btnServiceClick(ActionEvent event) {

		if (btnService_visible) {
			btnService.setStyle("-fx-background-color: #f3e5f5; ");
			btnService_visible = false;
		} else {
			btnService.setStyle("-fx-background-color: #ff6090; ");
			btnService_visible = true;
		}

	}

	private final ObservableList<Client> clients = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		Name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		Email.setCellValueFactory(new PropertyValueFactory<>("email"));
		service.setCellValueFactory(new PropertyValueFactory<>("service"));
		other.setCellValueFactory(new PropertyValueFactory<>("other"));

		Client client1 = new Client(1, "Abderrahim", "aabder@gmail.com", "Jardinage", 2);
		Client client2 = new Client(2, "Thomas", "tthom@gmail.com", "Jardinage", 3);
		Client client3 = new Client(3, "Lydia", "llydia@gmail.com", "Cuisinier", 1);
		Client client4 = new Client(4, "Essaid", "eessaid@gmail.com", "Jardinage", 2);
		Client client5 = new Client(3, "Dame", "dame@gmail.com", "Cuisinier", 4);
		Client client6 = new Client(4, "Killian", "Kil@gmail.com", "Cuisinier", 2);

		clients.addAll(client1, client2, client3, client4, client5, client6);

		FilteredList<Client> listFiltred = new FilteredList<>(clients, b -> true);

		filterField.textProperty().addListener((observable, oldInput, input) -> {
			listFiltred.setPredicate(client -> {
				if (input == null || input.isEmpty() || input.toString().length() == 1) {
					return true;
				}
				String toLowerCaseFilter = input.toLowerCase();

				if (client.getFirstName().toLowerCase().indexOf(toLowerCaseFilter) != -1 && btnName_visible) {
					return true;
				} else if (client.getService().toLowerCase().indexOf(toLowerCaseFilter) != -1 && btnService_visible) {
					return true;
				} else if (client.getEmail().toLowerCase().indexOf(toLowerCaseFilter) != -1 && btnEmail_visible) {
					return true;
				} else if (String.valueOf(client.getOther()).indexOf(toLowerCaseFilter) != -1)
					return true;
				else
					return false;
			});

		});

		SortedList<Client> output = new SortedList<>(listFiltred);
		output.comparatorProperty().bind(tableview.comparatorProperty());
		tableview.setItems(output);
	}

}
