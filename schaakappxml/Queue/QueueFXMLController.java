/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schaakappxml.Queue;

import Interfaces.ISessieManager;
import Server.SpelCommunicator;
import Spel.Spel;
import fontyspublisher.IRemotePublisherForListener;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import schaakappxml.SchaakAppGUI;

/**
 * FXML Controller class
 *
 * @author Luuk
 */
public class QueueFXMLController implements Initializable {

    private IRemotePublisherForListener publisher;
    @FXML
    private Button queue_btn;
    @FXML
    private Label looking_lbl;
    private Registry registry;
    private ISessieManager sessiemanager;
    private SpelCommunicator spelmanager;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        registry = LocateRegistry.getRegistry("localhost",1099);
        System.out.println("Connection registry succesfull");
        sessiemanager = (ISessieManager) registry.lookup("sessiemanager");
        System.out.println("Connection SessieManager succesfull");
        
        } 
        catch (RemoteException re) {
            System.err.println("Cannot establish connection to remote publisher");
        } catch (NotBoundException ex) {
            System.err.println("SessieManager not bound");
        }
    }    
    @FXML
    private void StartQueue() throws RemoteException, NotBoundException{
        looking_lbl.setVisible(true);
        queue_btn.setDisable(true);
        sessiemanager.startQueue(SchaakAppGUI.getSchaakapp().getLoggedin());
        String spelstring = null;
        while(spelstring == null){
            spelstring = sessiemanager.Refresh(SchaakAppGUI.getSchaakapp().getLoggedin());
        }
        
        System.out.println("game gevonden: " + spelstring);
        Spel spel = new Spel(spelstring, SchaakAppGUI.getSchaakapp().getLoggedin());
        try {
            StartSpel(spel);
        } catch (IOException ex) {
            Logger.getLogger(QueueFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void StartSpel(Spel spel) throws IOException{
            SchaakAppGUI.getSchaakapp().setSpel(spel);
            SchaakAppGUI.showSpel();
    }
    
}
