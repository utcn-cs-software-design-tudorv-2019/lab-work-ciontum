package DataAcces;

import Model.StudentData;
import BusinessLayer.dbConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    public javafx.scene.control.TextField id;
    @FXML
    private javafx.scene.control.TextField name;
    @FXML
    private Button btn;
    @FXML
    private TextField idp;
    @FXML
    private TextField numep;
    @FXML
    private TextField prenumep;
    @FXML
    private TextField cnpp;
    @FXML
    private javafx.scene.control.TextField grupa;
    public String s;
    public String s2;

    private dbConnection dc;
    private ObservableList<StudentData> data;

    private String sql = "SELECT * FROM students";

    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }
    public void test(String text){
        s=text;
    }
    public void test2(String text2){s2=text2;}
    @FXML
    private void addStudent(javafx.event.ActionEvent event) throws SQLException {
        String sqlInsert = "INSERT INTO students(ID,nume,grupa,username) VALUES (?,?,?,?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.name.getText());
            stmt.setString(3, this.grupa.getText());
            stmt.setString(4,this.s);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void profile() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/PresentationLayer/profile.fxml").openStream());
            Data datas=loader.getController();
            datas.test(s);
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("profile");
            userStage.setResizable(false);

            userStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void ViewProfile(ActionEvent event) {

        try {

            Stage stage = (Stage) this.btn.getScene().getWindow();
            stage.close();
            profile();

        } catch (Exception localException) {

        }
    }

    @FXML
    public void addinfo(ActionEvent event){
        String sqlInsert = "INSERT INTO client(ID,nume,prenume,CNP) VALUES (?,?,?,?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, this.idp.getText());
            stmt.setString(2, this.numep.getText());
            stmt.setString(3, this.prenumep.getText());
            stmt.setString(4, this.cnpp.getText());
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private TextField examenl;
    @FXML
    public void enroll(ActionEvent event){
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
            stmt2.setString(2,this.examenl.getText());
            stmt2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
