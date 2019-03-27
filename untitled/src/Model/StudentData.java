package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {
    private final StringProperty ID;
    private final StringProperty Name;
    private final StringProperty grupa;

    public StudentData(String id,String firstname,String lastname)
    {
        this.ID=new SimpleStringProperty(id);
        this.Name=new SimpleStringProperty(firstname);
        this.grupa=new SimpleStringProperty(lastname);


    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getGrupa() {
        return grupa.get();
    }

    public StringProperty grupaProperty() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa.set(grupa);
    }
}
