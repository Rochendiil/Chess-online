/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schaakappxml;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luuk
 */
public class SchaakAppGUI  extends Application{
    private static Stage stage;
    private static FXMLLoader fxmlLoader;
    public static FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }
    public static SchaakApp schaakapp;
    
    public static SchaakApp getSchaakapp() {
        return schaakapp;
    }

    public static void setSchaakapp(SchaakApp schaakapp) {
        SchaakAppGUI.schaakapp = schaakapp;
    }


    /**
     * start method
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        SchaakAppGUI.schaakapp = new SchaakApp();
        SchaakAppGUI.stage = stage;
        showLogin();
        //showSpel();
    }
    /**
    * Laat de Login pagina zien.
    * @throws IOException
    */
    public static void showLogin() throws IOException{
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(SchaakAppGUI.class.getResource("Login/LoginFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();     
    }
    
    public static void showQueue() throws IOException{
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(SchaakAppGUI.class.getResource("Queue/QueueFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Queue");
        stage.show();     
    }
    public static void showSpel() throws IOException{
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(SchaakAppGUI.class.getResource("Spel/SpelFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Spel");
        stage.show();     
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
