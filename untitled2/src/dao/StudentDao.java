package dao;

import Model.NoteData;
import Model.StudentData;
import dbUtil.dbConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.apache.log4j.helpers.Loader.getResource;

public class StudentDao implements Initializable {

    public static String s;
    private dbConnection dc;

    private String sql = "SELECT * FROM students";

    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }
    public void test(String text){
        s=text;
    }
    @FXML
    public static void addStudent(StudentData student)  {
        String sqlInsert = "INSERT INTO students(ID,nume,grupa,username) VALUES (?,?,?,?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, student.getID());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getGrupa());
            stmt.setString(4, s);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addinfo(ActionEvent event){
        String sqlInsert = "INSERT INTO client(ID,nume,prenume,CNP) VALUES (?,?,?,?)";
        try {
            Connection conn = dbConnection.getConnection();
        //    PreparedStatement stmt = conn.prepareStatement(sqlInsert);
        //    stmt.setString(1, this.idp.getText());
        //    stmt.setString(2, this.numep.getText());
        //    stmt.setString(3, this.prenumep.getText());
         //   stmt.setString(4, this.cnpp.getText());
          //  stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public static void enroll(NoteData note){
        String sqlInsert2 = "INSERT INTO examene(nume,examen) VALUES (?,?)";
        String sqlView2="select nume from students where username=?";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlView2);
            stmt.setString(1, s);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                s=rs.getString("nume");
            }
            PreparedStatement stmt2=conn.prepareStatement(sqlInsert2);
            stmt2.setString(1,s);
            stmt2.setString(2,note.getExamen());
            stmt2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
