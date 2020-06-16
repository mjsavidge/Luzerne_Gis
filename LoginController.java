package loginapp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import workers.WorkersController;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML private Label dbStatus;
    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML private ComboBox<option> combo;
    @FXML private Button login;
    @FXML private Label loginStatus;

    public void initialize(URL url, ResourceBundle rb){
        if(this.loginModel.isDataBaseConnected()){
            this.dbStatus.setText("Connected to database");
        }
        else{
            this.dbStatus.setText("Not Connected to database");
        }
        this.combo.setItems(FXCollections.observableArrayList(option.values()));
    }

    @FXML public void Login(javafx.event.ActionEvent actionEvent){
        try{
            if(this.loginModel.isLogin(this.userName.getText(), this.password.getText(), ((option)this.combo.getValue()).toString())){
                Stage stage = (Stage)this.login.getScene().getWindow();
                stage.close();
                switch(((option)this.combo.getValue()).toString()){
                    case "Admin":
                        adminLogin();
                        break;
                    case "Worker":
                        workerLogin();
                        break;
                }
            }
            else{
                this.loginStatus.setText("Wrong Credentials");
            }
        }
        catch(Exception localException){
            localException.printStackTrace();
        }
    }

    public void workerLogin(){
        try{
            Stage worker = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/workers/workerFXML.fxml").openStream());

            WorkersController workersController = (WorkersController)loader.getController();

            Scene scene = new Scene(root);
            worker.setScene(scene);
            worker.setTitle("Employee Dashboard");
            worker.setResizable(false);
            worker.show();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void adminLogin(){
        try{
            Stage admin = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/admin/Admin.fxml").openStream());

            AdminController adminController = (AdminController)loader.getController();

            Scene scene = new Scene(root);
            admin.setScene(scene);
            admin.setTitle("Admin Dashboard");
            admin.setResizable(false);
            admin.show();

        }
        catch(IOException eq){
            eq.printStackTrace();
        }
    }

}
