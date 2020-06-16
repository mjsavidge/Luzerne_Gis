package admin;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import workers.DeleteMap;
import workers.InsertMap;


import java.io.IOException;


public class AdminController{
    InsertMap insert_Map = new InsertMap();
    DeleteMap delete_Map = new DeleteMap();

    @FXML private Button signOutButton;
    @FXML private Button download;

    // to exit the program
    @FXML public void SignOut(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage)signOutButton.getScene().getWindow();
        stage.close();
        signIn();
    }
    // download maps
    @FXML public void Download(javafx.event.ActionEvent actionEvent){
        mapDownload();
    }

    // new scene for signing out of admin dashboard
    public void signIn(){
        try{
            Stage signIn = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/loginapp/login.FXML").openStream());

            Scene scene = new Scene(root);
            signIn.setScene(scene);
            signIn.setTitle("Luzerne County GIS System");
            signIn.show();


        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    // end of function

    // download maps from sql sever
    public void mapDownload(){

    }

}
