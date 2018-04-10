/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interfaces.ISessieManager;
import Interfaces.ISpelCommunicator;
import Spel.Spel;
import Spel.Speler;
import fontyspublisher.IRemotePublisherForListener;
import fontyspublisher.Publisher;
import fontyspublisher.RemotePublisher;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Luuk
 */
public class ServerMain extends Application{

    private SessieManager sessieManager;
    private SpelCommunicator spelmanager;
    private Registry registry;
    private static final int REGISTRYPORT = 1099; 
    private InetAddress localhost;
    private RemotePublisher publisher;
    private ArrayList<Speler> queue;
    private ArrayList<Spel> spellijst;
    private ISpelCommunicator spelcommunicator;
    public ArrayList<Spel> getSpellijst() {
        return spellijst;
    }
    public ArrayList<Speler> getQueue() {
        return queue;
    }
    private int id;
        
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        queue = new ArrayList<>();
        spellijst = new ArrayList<>();
        id =0;
        try {
            registry = LocateRegistry.createRegistry(REGISTRYPORT);
            publisher = new RemotePublisher();
            this.localhost = InetAddress.getLocalHost();
            System.out.println("SERVER ::: Registry aangemaakt op ip: " + localhost.getHostAddress() + " port:" + REGISTRYPORT);
            } 
        catch (RemoteException ex) {
            /// Logger.getLogger(Dobbelsteen.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SERVER::: FAILED to create Registry. There will be no publisher.");
            return;
            } 
        ISessieManager sessiemanager = new SessieManager(this);
        registry.rebind("publisher", publisher);
        spelcommunicator = new SpelCommunicator(this);
        registry.rebind("sessiemanager", sessiemanager);
        System.out.println("SERVER ::: Connection with remote publisher established");
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
    public void addSpelerToQueue(Speler speler){
        queue.add(speler);
        if(queue.size() == 2){
            id++;
            Spel spel = new Spel(queue.get(0), queue.get(1), id);
            queue.remove(1);
            queue.remove(0);
            spellijst.add(spel);
            try {
                spelcommunicator.register(spel.getId() + ", zwart" );
                spelcommunicator.register(spel.getId() + ", wit" );
                spelcommunicator.register(spel.getId() + ", opgevenwit");
                spelcommunicator.register(spel.getId() + ", opgevenzwart");
            } catch (RemoteException ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        
    }
}
