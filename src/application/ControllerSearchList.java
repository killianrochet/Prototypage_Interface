package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControllerSearchList implements Initializable {
	
	 public List<String> list = new ArrayList<>();

    ArrayList<String> strings = new ArrayList<>(
            Arrays.asList("salut", "hello","holla", "bonjour", "il fait beau aujourdhuit",
                    "test de recherche","ab cd")
    );

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
    	Task task = new Task<Void>()  {
    		 @Override public Void call() throws Exception{
    			 Platform.runLater(() ->  {
    			 searchList(searchBar.getText(), strings);
    			 
    			 });
    			 return null;
    	 }
  	   
    	};
    	new Thread(task).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(strings);
    }

    private void searchList(String searchStrings, List<String> listOfStrings) {
    	
 
    	   	 List<String> searchWordsArray = Arrays.asList(searchStrings.trim().split(" "));
    	   	 
    	     if(searchWordsArray.toString().length()>5) {
    	    	 System.out.println("la longueur est de : " + searchWordsArray.toString().length() );
    	    	 list = listOfStrings.stream().filter(input -> {
    	    		 return searchWordsArray.stream().allMatch(word ->
    	                 input.toLowerCase().contains(word.toLowerCase()));
    	    	 		}).collect(Collectors.toList());
    	    	 		System.out.println("je suis ici");
    	    	 		if( list.isEmpty()) 
    	    	 			listView.getItems().addAll(listOfStrings);
    	    	 		else
    	    	 			listView.getItems().addAll(list);
    	     } 	     
    	     else{ 	    	 	
    	    	 	listView.getItems().addAll(list);
    	    	 	System.out.println("je suis là");
    	     }
    
    }
    
}