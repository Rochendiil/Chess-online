/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schaakappxml.Login;

import Spel.Speler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import schaakappxml.SchaakAppGUI;

/**
 *
 * @author Luuk
 */
public class LoginFXMLController implements Initializable {
    
    @FXML
    public TextField username_txt;

    @FXML
    private TextField pass_txt;
    
    @FXML
    public void Login() throws IOException{
        Speler speler = new Speler(username_txt.getText());
        SchaakAppGUI.schaakapp.setLoggedin(speler);
        SchaakAppGUI.showQueue();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
