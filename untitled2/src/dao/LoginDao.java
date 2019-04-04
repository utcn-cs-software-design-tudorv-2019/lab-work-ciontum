package dao;

import Model.LoginModel;
import PresentationLayer.AdminController;
import PresentationLayer.StdController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginDao  {
    static LoginModel loginModel=new LoginModel();



    @FXML
    public static int Logins(String user,String pass)
    {
        try
        {
            if(loginModel.isLogin(user,pass))
            {
                return 1;
            }
            else if (user.equals("admin"))
            {
                return 2;

            }
            else
            {
               return 3;
            }
        }
        catch (Exception localException)
        {

        }
        return 0;
    }

}

