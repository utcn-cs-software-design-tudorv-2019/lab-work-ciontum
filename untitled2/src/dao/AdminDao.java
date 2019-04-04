package dao;

import dbUtil.dbConnection;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDao implements Initializable {

    public static String s;
    public void test2(String text){
        s=text;
    }
    private dbConnection dc;
    private static String sql="SELECT * FROM students";

    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }

    @FXML
    public static List<StudentData> loadStudentData() {
        ObservableList<StudentData> list;
        list=FXCollections.observableArrayList();
        try{
            Connection conn=dbConnection.getConnection();



             StudentData student=new StudentData();
            ResultSet rs=conn.createStatement().executeQuery(sql);

            while(rs.next())
            {
                list.add(new StudentData(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch(SQLException e)
        {
            System.err.println("Eror"+e);
        }
       return list;

    }

    @FXML
    public static void addStudent(StudentData student,String username){
        String sqlInsert="INSERT INTO students(ID,nume,grupa,username) VALUES (?,?,?,?)";
        try{
            Connection conn=dbConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sqlInsert);
            stmt.setString(1,student.getID());
            stmt.setString(2,student.getName());
            stmt.setString(3,student.getGrupa());
            stmt.setString(4,username);

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public static void deleteStudent(int l2,String Temp) throws  SQLException{
        String sqlDelete="DELETE FROM students WHERE ID = ?";
        if(l2>=0)
        {
            Connection conn=dbConnection.getConnection();
            PreparedStatement pst=conn.prepareStatement(sqlDelete);
            pst.setString(1,Temp);
            pst.execute();
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
    public static void updateStudent(StudentData student,int l2,String temp,String user) throws  SQLException{

        String sqlUpdate="UPDATE students SET ID = ? , nume = ? ,grupa = ? ,username=? WHERE ID = ?";

        if(l2>=0)
        {
            Connection conn=dbConnection.getConnection();

            PreparedStatement pst2=conn.prepareStatement(sqlUpdate);
            pst2.setString(5,temp);
            pst2.setString(1,student.getID());
            pst2.setString(2,student.getName());
            pst2.setString(3,student.getGrupa());
            pst2.setString(4,user);
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
    public static void addGrade(NoteData nota,int l,String temp1,String temp2)throws SQLException{
        String sqlUpdate="UPDATE examene SET Nota = ? where nume=? and examen=?";


        if(l>=0)
        {
            Connection conn=dbConnection.getConnection();

            PreparedStatement pst2=conn.prepareStatement(sqlUpdate);

            pst2.setString(1,nota.getNota());
            pst2.setString(2,temp1);
            pst2.setString(3,temp2);

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
    public static List<NoteData> loadStudentData2() throws SQLException {
        String sql2="select * from examene";
        ObservableList<NoteData> list;
        list=FXCollections.observableArrayList();
        try{
            Connection conn=dbConnection.getConnection();


            ResultSet rs=conn.createStatement().executeQuery(sql2);

            while(rs.next())
            {
                list.add(new NoteData(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch(SQLException e)
        {
            System.err.println("Eror"+e);
        }
        return list;

    }
}
