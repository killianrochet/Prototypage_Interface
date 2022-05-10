package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Sejour {
	public ImageView PhotoColumn  ;
	public StringProperty HoteColumn  ;
	public StringProperty  NbrPersonnesRechercheesColumn;
	public StringProperty NbrJoursColumn  ;
	public StringProperty RestaurationColumn ;
	public StringProperty CompetancesColumn;
	public StringProperty DatesColumn;
	public Button button;
	
	public Sejour() {
		super();
	}
	public Sejour(String Hote, String NbrPersonnesRecherchees, String NbrJours, String Restauration, String Competances, String Dates, ImageView Photo)
	{
		this.PhotoColumn =  Photo; 
		this.HoteColumn = new SimpleStringProperty(Hote) ;
		this.NbrPersonnesRechercheesColumn = new SimpleStringProperty(NbrPersonnesRecherchees)  ;
		this.NbrJoursColumn = new SimpleStringProperty(NbrJours);
		this.RestaurationColumn =  new SimpleStringProperty(Restauration);  
		this.CompetancesColumn = new SimpleStringProperty(Competances) ;
		this.DatesColumn = new SimpleStringProperty(Dates) ;
		this.button = new Button("Reserver");
		this.button.setStyle( "-fx-background-color: #3C3D5B ; -fx-text-fill: white; -fx-font-size: 10px;");
		
	}
	
	
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	
	public ImageView getPhotoColumn() {
		return PhotoColumn;
	}
	public void setPhotoColumn(ImageView photoColumn) {
		PhotoColumn = photoColumn;
	}
	
	public final StringProperty HoteColumnProperty() {
		return this.HoteColumn ;
	}
	public final StringProperty NbrPersonnesRechercheesColumnProperty() {
		return this.NbrPersonnesRechercheesColumn ;
	}
	public final StringProperty NbrJoursColumnProperty() {
		return this.NbrJoursColumn ;
	}
	public final StringProperty RestaurationColumnProperty() {
		return this.RestaurationColumn ;
	}
	public final StringProperty CompetancesColumnProperty() {
		return this.CompetancesColumn ;
	}
	public final StringProperty DatesColumnProperty() {
		return this.DatesColumn ;
	}
	
	public final String getHote() {
		
		return HoteColumn.get();
	}
	public final String getNbrPersonnesRecherchees(){
			
			return NbrPersonnesRechercheesColumn.get();
		}
	public final String getNbrJours() {
		
		return NbrJoursColumn.get();
	}
	public final String getRestauration() {
		
		return RestaurationColumn.get();
	}
	public final String getDates() {
		
		return DatesColumn.get();
	}
		
	
	
}