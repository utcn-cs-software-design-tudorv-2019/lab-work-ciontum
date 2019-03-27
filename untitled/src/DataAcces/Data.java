package DataAcces;

import Model.StudentData;
import BusinessLayer.dbConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Data implements Initializable {



    @FXML
    private Label idl;
    @FXML
    private Label idl1;

    @FXML
    private Label numel;
    @FXML
    private Label grupal;

    private dbConnection dc;
    private ObservableList<StudentData> data;

    private String sql = "SELECT * FROM students";

    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }
    public String s;
    public void test(String text)
    {
        s=text;
    }
    @FXML
    private TextField idt;
    public void View(ActionEvent event){
        String sqlNume = "select ID,nume,grupa from students where students.username= ?";

        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlNume);
            stmt.setString(1, s);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                this.idl.setText(rs.getString("ID"));
                this.numel.setText(rs.getString("nume"));
                this.grupal.setText(rs.getString("grupa"));
            }
            rs.close();
            stmt.close();

        }
        catch (SQLException e)
        {
            System.err.println("Eror"+e);
        }
    }
    public void ViewNota(ActionEvent event){
        String sqlView="select nota from examene where examen=? and nume=?";
        String sqlView2="select nume from students where username=?";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlView2);
            stmt.setString(1, s);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                s=rs.getString("nume");
            }
            PreparedStatement stmt2=conn.prepareStatement(sqlView);
            stmt2.setString(1,this.idt.getText());
            stmt2.setString(2,s);
            ResultSet rs2=stmt2.executeQuery();
            while(rs2.next()) {
               this.idl1.setText(rs2.getString("nota"));
            }
            rs.close();
            stmt.close();

        }
        catch (SQLException e)
        {
            System.err.println("Eror"+e);
        }
    }
}
