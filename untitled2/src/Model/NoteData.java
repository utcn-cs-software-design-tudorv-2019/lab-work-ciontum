package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NoteData {
    private final StringProperty nume;
    private final StringProperty examen;
    private final StringProperty nota;

    public NoteData(String id,String firstname,String lastname)
    {
        this.nume=new SimpleStringProperty(id);
        this.examen=new SimpleStringProperty(firstname);
        this.nota=new SimpleStringProperty(lastname);


    }
    public NoteData()
    {
        this.nume=null;
        this.examen=null;
        this.nota=null;


    }
    public String getNume() {
        return nume.get();
    }

    public StringProperty numeProperty() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume.set(nume);
    }

    public String getExamen() {
        return examen.get();
    }

    public StringProperty examenProperty() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen.set(examen);
    }

    public String getNota() {
        return nota.get();
    }

    public StringProperty notaProperty() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota.set(nota);
    }
}

