package loginapp;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

public class LoginApp extends Application {
    public void start(Stage stage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Luzerne County GIS System");
        stage.show();


    }
    public static void main(String[] args){
        LoginApp loginApp = new LoginApp();

        loginApp.launch(args);
    }
}
