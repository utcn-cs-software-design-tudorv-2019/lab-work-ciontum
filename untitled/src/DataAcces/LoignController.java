package DataAcces;

import Model.LoginModel;
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

public class LoignController implements Initializable {
    LoginModel loginModel=new LoginModel();
    @FXML
    private Label dbstatus1;
    @FXML
    public TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginbtn;
    @FXML
    private Label dbstatus12;
    public String s;

    public void initialize(URL url, ResourceBundle rb)
    {
        if(this.loginModel.isDataBaseConnected())
        {
            this.dbstatus1.setText("Connected");
        }else{
            this.dbstatus1.setText("not Connected");

        }
    }

    @FXML
    public void Logins(ActionEvent event)
    {
        try
        {
            if(this.loginModel.isLogin(this.username.getText(),this.password.getText()))
            {
                Stage stage=(Stage)this.loginbtn.getScene().getWindow();
                stage.close();
                this.s=this.username.getText();

                studentLogin();


            }
            else if (this.username.getText().toString().equals("admin"))
            {
                Stage stage=(Stage)this.loginbtn.getScene().getWindow();
                stage.close();

                adminLogin();
            }
            else
            {
                this.dbstatus12.setText("nah");
            }
        }
        catch (Exception localException)
        {

        }
    }
    public void studentLogin()
    {
        try{
            Stage userStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root=(Pane)loader.load(getClass().getResource("/PresentationLayer/studentsfx.fxml").openStream());

           // StudentController  studentController= (StudentController)loader.getController();

            StudentController  studentController= (StudentController)loader.getController();
            studentController.test(s);
            Scene scene=new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student dash");
            userStage.setResizable(false);
            userStage.show();
        }

        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void adminLogin()
    {
        try{
            Stage adminStage=new Stage();
            FXMLLoader loader2=new FXMLLoader();
            Pane root2=(Pane)loader2.load(getClass().getResource("/PresentationLayer/adminfx.fxml").openStream());

            AdminController adminController= (AdminController)loader2.getController();
            adminController.test2(s);

            Scene scene=new Scene(root2);
            adminStage.setScene(scene);
            adminStage.setTitle("admin dash");
            adminStage.setResizable(false);
            adminStage.show();
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }
    public String getusername(){
        return s;
    }
}

