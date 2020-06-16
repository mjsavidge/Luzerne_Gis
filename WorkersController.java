package workers;

import dbUtil.dbConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

import javafx.util.Callback;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;


public class WorkersController implements Initializable {
    DisplayMap displayMap = new DisplayMap();
    InsertMap insertMap = new InsertMap();
    DeleteMap deleteMap = new DeleteMap();

    ObservableList<ObservableList> map_data;

    @FXML Button sign_out_button;
    @FXML Button btn_look_up;
    @FXML TextField dbStatusMaps;
    @FXML TableView worker_map_display;
    @FXML Label fileOne;
    @FXML Label fileTwo;
    @FXML Label fileThree;
    @FXML Label fileFour;
    @FXML Button btnUpload;
    @FXML TextField mapName;
    @FXML Label uploadState;
    @FXML Button delete;
    @FXML TextField delete_map_text;
    //
    //
    //************************************************************************checking connections of database;
    public void initialize(URL url, ResourceBundle rb){
        if(this.displayMap.isDataBaseConnected()){
            this.dbStatusMaps.setText("DB Connected");
        }
        else{
            this.dbStatusMaps.setText("DB Connection Error!");
        }

        if(this.insertMap.isDataBaseConnected()){
            this.uploadState.setText("DB Connected");
        }
        else{
            this.uploadState.setText("DB error");
        }

    }
    //************************************************************************end of checking the database connection;
    //
    //
    //************************************************************************signing out;
    @FXML public void signOut(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage)sign_out_button.getScene().getWindow();
        stage.close();
        signIn();
    }

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
    //*************************************************************************end of signing out;
    //
    //
    //*************************************************************************start of map look up;
    @FXML public void mapLookUp(javafx.event.ActionEvent actionEvent) throws Exception{
        //:TODO make a dynamic list of map names
        this.map_data = FXCollections.observableArrayList();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT map_name FROM Map";
        try{
           pr = this.displayMap.connection.prepareStatement(sql);
           rs = pr.executeQuery();
            for(int i = 0; i < rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                worker_map_display.getColumns().addAll(col);
            }
            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                map_data.add(row);
            }
            worker_map_display.setItems(null);
            worker_map_display.setItems(map_data);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }




    }
    //************************************************************************end of display map;
    //
    //
    //************************************************************************start of browse map;
    @FXML public void browse(javafx.event.ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("tfwx, tif, aux and ovr ", "*.tif", "*.tfwx", "*.aux", "*.ovr");
        fc.getExtensionFilters().add(filter);
        fc.setTitle("Browse Maps");
        File file = fc.showOpenDialog(null);
        if(file.getName().contains(".tif")){
            this.fileOne.setText(file.getName());
        }
        else if(file.getName().contains(".tfwx")){
            this.fileTwo.setText(file.getName());
        }
        else if(file.getName().contains(".aux")){
            this.fileThree.setText(file.getName());
        }
        else{
            this.fileFour.setText(file.getName());
        }



    }

//**********************************************************************end of browse
//
//
//**********************************************************************start of upload
    @FXML public void btnUpload(javafx.event.ActionEvent actionEvent) throws Exception{

        if(this.mapName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill out ever option");
            alert.setContentText("Please enter map name to be able to proceed");
            alert.showAndWait();
        }
        else{
            try{
                insertMap.uploadMap(this.fileOne.getText(), this.fileTwo.getText(), this.fileThree.getText(), this.fileFour.getText(), this.mapName.getText());
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
//*******************************************************************end of upload
//******************************************************************begining of delete file
    @FXML public void deletebtn(javafx.event.ActionEvent actionEvent)throws Exception{
       if(this.delete_map_text.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Error");
           alert.setHeaderText("Please fill out ever option");
           alert.setContentText("Please enter map name to be able to proceed");
           alert.showAndWait();
       }
       else{
           deleteMap.removeMap(this.delete_map_text.getText());
       }
    }
}
