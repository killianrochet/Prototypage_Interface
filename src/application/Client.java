package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Client {    
    private  final SimpleIntegerProperty ID;
    private  final SimpleStringProperty firstName;
    private  final SimpleStringProperty email;
    private  final SimpleStringProperty service;
    private  final SimpleIntegerProperty ohter;
    
    Client(Integer id, String firstname, String mail, String service, Integer other)
    {      
       this.ID = new SimpleIntegerProperty(id);
       this.firstName = new SimpleStringProperty(firstname);
       this.email =  new SimpleStringProperty(mail);
       this.service =  new SimpleStringProperty(service);
       this.ohter =  new SimpleIntegerProperty(other);
    
    }
    
     
    public int getID() {
        return ID.get();
    }

    public void setID(int id) {
        this.ID.set(id);
    }
    
   
  
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstname) {
        firstName.set(firstname);
    }
    
    

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String mail) {
        email.set(mail);
    }
    
    
    public String getService() {
        return service.get();
    }

    public void setService(String service) {
        this.service.set(service);
    }
    
    public int getOther() {
        return ohter.get();
    }

    public void serOther(int other) {
        this.ohter.set(other);
    }
    
    
}
    
