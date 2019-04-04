package PresentationLayer;

import BusinessLayer.AdminBLL;
import Model.NoteData;
import Model.StudentData;
import dbUtil.dbConnection;
import gherkin.lexer.No;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TableView<StudentData> studenttable;
    @FXML
    private TableView<NoteData> studenttable1;
    @FXML
    private TableColumn<StudentData,String> idcolumn;
    @FXML
    private TableColumn<StudentData,String> namecolumn;
    @FXML
    private TableColumn<StudentData,String> grupacolumn;
    @FXML
    private TableColumn<NoteData,String> idcolumn1;
    @FXML
    private TableColumn<NoteData,String> namecolumn1;
    @FXML
    private TableColumn<NoteData,String> grupacolumn1;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField grupa;
    @FXML
    private TextField id1;
    @FXML
    private TextField cnp;

    public void Load(ActionEvent event)
    {
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("ID"));
        this.namecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("Name"));
        this.grupacolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("Grupa"));
        this.studenttable.setItems(null);
        this.studenttable.setItems((ObservableList)AdminBLL.load());
    }
    public void Add(ActionEvent event)
    {
        String id=this.id.getText();
        String nume=this.name.getText();
        String grupa=this.grupa.getText();
        String user=this.cnp.getText();
        AdminBLL.addStudent(id,nume,grupa,user);

    }
    public void Delete(ActionEvent event) throws SQLException
    {
        int selectedIndex=studenttable.getSelectionModel().getSelectedIndex();
        StudentData asm=(StudentData)studenttable.getSelectionModel().getSelectedItem();
        String tempID=asm.getID();
        studenttable.getItems().remove(selectedIndex);
        AdminBLL.deleteStudent(selectedIndex,tempID);
    }
    public void Update(ActionEvent event) throws SQLException
    {
        String id=this.id.getText();
        String nume=this.name.getText();
        String grupa=this.grupa.getText();
        String user=this.cnp.getText();
        int selectedIndex=studenttable.getSelectionModel().getSelectedIndex();
        StudentData asm=(StudentData)studenttable.getSelectionModel().getSelectedItem();
        String tempID=asm.getID();

        AdminBLL.updateStudent(id,nume,grupa,selectedIndex,tempID,user);
        studenttable.setItems((ObservableList)AdminBLL.load());
    }
    public void AddGrade(ActionEvent event) throws SQLException
    {
        String nota=this.id1.getText();
        int selectedIndex=studenttable1.getSelectionModel().getSelectedIndex();
        NoteData asm=(NoteData)studenttable1.getSelectionModel().getSelectedItem();
        NoteData asm2=(NoteData) studenttable1.getSelectionModel().getSelectedItem();
        String temp=asm.getNume();
        String temp2=asm2.getExamen();

        AdminBLL.addGrade(nota,selectedIndex,temp,temp2);
    }
    public void LoadGrade(ActionEvent event) throws SQLException
    {

        this.idcolumn1.setCellValueFactory(new PropertyValueFactory<NoteData,String>("nume"));
        this.namecolumn1.setCellValueFactory(new PropertyValueFactory<NoteData,String>("examen"));
        this.grupacolumn1.setCellValueFactory(new PropertyValueFactory<NoteData,String>("nota"));
        this.studenttable1.setItems(null);
        this.studenttable1.setItems((ObservableList)AdminBLL.loadGrade());
    }
    dbConnection dc;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dc=new dbConnection();
    }
}
