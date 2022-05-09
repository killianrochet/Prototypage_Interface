package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sejour {
	public StringProperty HoteColumn  ;
	public IntegerProperty  NbrPersonnesRechercheesColumn = new SimpleIntegerProperty();
	public IntegerProperty NbrJoursColumn  = new SimpleIntegerProperty();
	public StringProperty RestaurationColumn ;
	public StringProperty CompetencesColumn;
	public StringProperty DatesColumn;
	
	public Sejour() {
		super();
	}
	
	public Sejour(String Hote, int NbrPersonnesRecherchees, int NbrJours, String Restauration, String Competences, String Dates)
	{
		this.HoteColumn = new SimpleStringProperty(Hote) ;
		this.NbrPersonnesRechercheesColumn = new SimpleIntegerProperty(NbrPersonnesRecherchees)  ;
		this.NbrJoursColumn = new SimpleIntegerProperty(NbrJours);
		this.RestaurationColumn =  new SimpleStringProperty(Restauration);  
		this.CompetencesColumn = new SimpleStringProperty(Competences);
		this.DatesColumn = new SimpleStringProperty(Dates);
	}
	 
	public final StringProperty HoteColumnProperty() {
		return this.HoteColumn;
	}
	
	public final IntegerProperty NbrPersonnesRechercheesColumnProperty() {
		return this.NbrPersonnesRechercheesColumn ;
	}
	
	public final IntegerProperty NbrJoursColumnProperty() {
		return this.NbrJoursColumn ;
	}
	
	public final StringProperty RestaurationColumnProperty() {
		return this.RestaurationColumn ;
	}
	
	public final StringProperty CompetencesColumnProperty() {
		return this.CompetencesColumn ;
	}
	
	public final StringProperty DatesColumnProperty() {
		return this.DatesColumn ;
	}
	
	public final String getHote() {
		return HoteColumn.get();
	}
	public final Integer getNbrPersonnesRecherchees(){
		return NbrPersonnesRechercheesColumn.get();
	}
	public final Integer getNbrJours() {
		return NbrJoursColumn.get();
	}
	
	public final String getRestauration() {
		return RestaurationColumn.get();
	}
	
	public final String getDates() {
		return DatesColumn.get();
	}
}