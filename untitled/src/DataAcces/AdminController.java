package DataAcces;

import BusinessLayer.dbConnection;
import Model.NoteData;
import Model.StudentData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField id1;
    @FXML
    private TextField grupa;
    @FXML
    private TableView<StudentData> studenttable;
    @FXML
    private TableColumn<StudentData,String> idcolumn;
    @FXML
    private TableColumn<StudentData,String> namecolumn;
    @FXML
    private TableColumn<StudentData,String> grupacolumn;
    @FXML
    private TableColumn<StudentData,String> dobcolumn;
    @FXML
    private TextField cnp;
    @FXML
    private TableView<NoteData> studenttable1;
    @FXML
    private TableColumn<NoteData,String> idcolumn1;
    @FXML
    private TableColumn<NoteData,String> namecolumn1;
    @FXML
    private TableColumn<NoteData,String> grupacolumn1;
    @FXML
    private TableColumn<NoteData,String> dobcolumn1;

    public String s;
    public void test2(String text){
        s=text;
    }
    private dbConnection dc;
    private ObservableList<StudentData> data;
    private ObservableList<NoteData> data2;
    private String sql="SELECT * FROM students";

    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }

    @FXML
    private void loadStudentData(javafx.event.ActionEvent event) throws SQLException {
        try{
            Connection conn=dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();

            ResultSet rs=conn.createStatement().executeQuery(sql);

            while(rs.next())
            {
                this.data.add(new StudentData(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch(SQLException e)
        {
            System.err.println("Eror"+e);
        }
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("ID"));
        this.namecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("Name"));
        this.grupacolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("Grupa"));

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);

    }

    @FXML
    private void addStudent(javafx.event.ActionEvent event) throws  SQLException{
        String sqlInsert="INSERT INTO students(ID,nume,grupa,username) VALUES (?,?,?,?)";
        try{
            Connection conn=dbConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sqlInsert);
            stmt.setString(1,this.id.getText());
            stmt.setString(2,this.name.getText());
            stmt.setString(3,this.grupa.getText());
            stmt.setString(4,this.cnp.getText());
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void deleteStudent(javafx.event.ActionEvent event) throws  SQLException{

        String sqlDelete="DELETE FROM students WHERE ID = ?";
        int selectedIndex=studenttable.getSelectionModel().getSelectedIndex();
        String selectedItem=idcolumn.getText();
        StudentData asm=(StudentData)studenttable.getSelectionModel().getSelectedItem();
        String tempCNP=asm.getID();
        if(selectedIndex>=0)
        {
            Connection conn=dbConnection.getConnection();
            PreparedStatement pst=conn.prepareStatement(sqlDelete);
            pst.setString(1,tempCNP);
            pst.execute();
            conn.close();
            studenttable.getItems().remove(selectedIndex);

        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No selection was made");
            alert.setContentText("You have not selected");
            alert.showAndWait();
        }
        }
        @FXML
    private void updateStudent(javafx.event.ActionEvent event) throws  SQLException{

        String sqlUpdate="UPDATE students SET ID = ? , nume = ? ,grupa = ? WHERE ID = ?";
        String sqlDelete="DELETE FROM students WHERE ID = ?";
        int selectedIndex=studenttable.getSelectionModel().getSelectedIndex();
        String selectedItem=idcolumn.getText();
        StudentData asm2=(StudentData)studenttable.getSelectionModel().getSelectedItem();
        String tempCNP2=asm2.getID();
        if(selectedIndex>=0)
        {
            Connection conn=dbConnection.getConnection();

            PreparedStatement pst2=conn.prepareStatement(sqlUpdate);
            pst2.setString(4,tempCNP2);
            pst2.setString(1,this.id.getText());
            pst2.setString(2,this.name.getText());
            pst2.setString(3,this.grupa.getText());
            pst2.execute();
            conn.close();


        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No selection was made");
            alert.setContentText("You have not selected");
            alert.showAndWait();
        }
    }

    @FXML
    private void addGrade(javafx.event.ActionEvent event)throws SQLException{
        String sqlUpdate="UPDATE examene SET Nota = ? where nume=? and examen=?";

        int selectedIndex=studenttable1.getSelectionModel().getSelectedIndex();
        String selectedItem=idcolumn1.getText();
        String selectedItem2=namecolumn1.getText();
        NoteData asm2=(NoteData) studenttable1.getSelectionModel().getSelectedItem();
        NoteData asm3=(NoteData) studenttable1.getSelectionModel().getSelectedItem();
        String tempname2=asm2.getNume();
        String tempname3=asm3.getExamen();
        if(selectedIndex>=0)
        {
            Connection conn=dbConnection.getConnection();

            PreparedStatement pst2=conn.prepareStatement(sqlUpdate);

            pst2.setString(1,this.id1.getText());
            pst2.setString(2,tempname2);
            pst2.setString(3,tempname3);

            pst2.execute();
            conn.close();


        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No selection was made");
            alert.setContentText("You have not selected");
            alert.showAndWait();
        }
    }
    @FXML
    private void loadStudentData2(javafx.event.ActionEvent event) throws SQLException {
        String sql2="select * from examene";
        try{
            Connection conn=dbConnection.getConnection();
            this.data2= FXCollections.observableArrayList();

            ResultSet rs=conn.createStatement().executeQuery(sql2);

            while(rs.next())
            {
                this.data2.add(new NoteData(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch(SQLException e)
        {
            System.err.println("Eror"+e);
        }
        this.idcolumn1.setCellValueFactory(new PropertyValueFactory<NoteData,String>("nume"));
        this.namecolumn1.setCellValueFactory(new PropertyValueFactory<NoteData,String>("examen"));
        this.grupacolumn1.setCellValueFactory(new PropertyValueFactory<NoteData,String>("nota"));

        this.studenttable1.setItems(null);
        this.studenttable1.setItems(this.data2);

    }
}
