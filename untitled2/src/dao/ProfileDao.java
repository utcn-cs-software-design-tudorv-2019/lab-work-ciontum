package dao;

import Model.NoteData;
import Model.StudentData;
import dbUtil.dbConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ProfileDao implements Initializable {

    private dbConnection dc;
    private String sql = "SELECT * FROM students";

    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }
    public static String s;
    public void test(String text)
    {
        s=text;
    }
    @FXML
    private TextField idt;
    public static List<StudentData> View(){
        String sqlNume = "select ID,nume,grupa from students where students.username= ?";
        ArrayList list=new ArrayList();

        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlNume);
            stmt.setString(1, s);
            System.out.println(s);
            StudentData student=new StudentData();
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getString("ID"));
                student.setID(rs.getString("ID"));
                student.setName(rs.getString("nume"));
                student.setGrupa(rs.getString("grupa"));

                list.add(student);

            }
            rs.close();
            stmt.close();

        }
        catch (SQLException e)
        {
            System.err.println("Eror"+e);
        }

        return list;
    }
    public static String ViewNota(NoteData note,String datas){
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
            stmt2.setString(1,note.getExamen());
            stmt2.setString(2,s);
            ResultSet rs2=stmt2.executeQuery();
            while(rs2.next()) {
               note.setNota(rs2.getString("nota"));
               datas=note.getNota();
            }
            rs.close();
            stmt.close();

        }
        catch (SQLException e)
        {
            System.err.println("Eror"+e);
        }
        return datas;
    }
}
