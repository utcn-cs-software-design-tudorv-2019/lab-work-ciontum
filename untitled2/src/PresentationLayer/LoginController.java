package PresentationLayer;

import BusinessLayer.LoginBLL;
import dao.AdminDao;
import dao.ProfileDao;
import dao.StudentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import sun.rmi.runtime.Log;

import java.awt.*;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginbtn;
    public static String s;
    @FXML
    public void Login(ActionEvent event) {
        String user = this.username.getText();
        String pass = this.password.getText();
        if (LoginBLL.Login(user, pass) == 1) {
            Stage stage = (Stage) this.loginbtn.getScene().getWindow();
            stage.close();
            s=user;
            studentLogin();
        }
        else
            if(LoginBLL.Login(user,pass)==2){
                Stage stage = (Stage) this.loginbtn.getScene().getWindow();
                stage.close();
                s=user;
                adminLogin();
            }
    }

    public void studentLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("studentsfx.fxml").openStream());

            //StudentDao  studentController= (StudentDao)loader.getController();
            StudentDao studentController = new StudentDao();
            ProfileDao data = new ProfileDao();
            StdController studentController2 = (StdController) loader.getController();
            studentController.test(s);
            data.test(s);
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student dash");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void adminLogin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader loader2 = new FXMLLoader();
            Pane root2 = (Pane) loader2.load(getClass().getResource("adminfx.fxml").openStream());

            AdminDao adminController = new AdminDao();
            AdminController adminController2 = (AdminController) loader2.getController();
            adminController.test2(s);

            Scene scene = new Scene(root2);
            adminStage.setScene(scene);
            adminStage.setTitle("admin dash");
            adminStage.setResizable(false);
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}