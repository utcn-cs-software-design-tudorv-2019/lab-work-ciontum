package PresentationLayer;

import BusinessLayer.StudentBLL;
import dbUtil.dbConnection;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.apache.log4j.helpers.Loader.getResource;

public class StdController implements Initializable {
    @FXML
    public javafx.scene.control.TextField id;
    @FXML
    private javafx.scene.control.TextField name;
    @FXML
    private Button btn;
    @FXML
    private TextField idp;

    @FXML
    private TextField examenl;
    private TextField numep;
    @FXML
    private TextField prenumep;
    @FXML
    private TextField cnpp;
    @FXML
    private javafx.scene.control.TextField grupa;
    public static String s;
    public void test(String text){
        s=text;
    }
    public void addStudent3(ActionEvent event)  {
        String id=this.id.getText();
        String nume=this.name.getText();
        String grupa=this.grupa.getText();
        StudentBLL.addStudent2(id,nume,grupa);
    }
    public void enroll(ActionEvent event)
    {
        String examen=this.examenl.getText();
//        print(examen);
        StudentBLL.enroll(null,examen,null);
    }
    public void viewProfile(ActionEvent event)
    {
        StudentBLL.viewProfile();
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
    public  void profile() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/PresentationLayer/profile.fxml").openStream());
           // ProfileDao datas=loader.getController();
            //datas.test(s);
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("profile");
            userStage.setResizable(false);

            userStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnection db=new dbConnection();
    }
}
