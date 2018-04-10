/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interfaces.ISpelCommunicator;
import Spel.Spel;
import Spel.Speler;
import Spel.ZetEvent;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.IRemotePublisherForListener;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import schaakappxml.Spel.SpelFXMLController;

/**
 *
 * @author Luuk
 */
public class SpelCommunicator extends UnicastRemoteObject implements ISpelCommunicator {
    private Spel spel;
    private ArrayList<Speler> spelersinqueue;
    private Registry registry;
    private IRemotePublisherForDomain publisherForDomain;
    private ServerMain server;
    private boolean connected;
    private SpelFXMLController controller;
    private IRemotePublisherForListener publisherForListener;
    private final int nrThreads = 10;
    private ExecutorService threadPool = null;
    
    public SpelCommunicator (SpelFXMLController controller) throws RemoteException{
        spelersinqueue = new ArrayList<>();
        threadPool = Executors.newFixedThreadPool(nrThreads);
        this.controller = controller;
        registry = LocateRegistry.getRegistry("localhost", 1099);
        connectToPublisher();    
    }
    public SpelCommunicator (ServerMain server) throws RemoteException{
        spelersinqueue = new ArrayList<>();
        threadPool = Executors.newFixedThreadPool(nrThreads);
        this.server = server;
        registry = LocateRegistry.getRegistry("localhost", 1099);
        connectToPublisher();    
    }


    
    /**
     * Establish connection with remote publisher.
     */
    public void connectToPublisher() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            publisherForDomain = (IRemotePublisherForDomain) registry.lookup("publisher");
            publisherForListener = (IRemotePublisherForListener) registry.lookup("publisher");
            connected = true;
            System.out.println("Connection with remote publisher established");
        } catch (RemoteException | NotBoundException re) {
            connected = false;
            System.err.println("Cannot establish connection to remote publisher");
            System.err.println("Run WhiteBoardServer to start remote publisher");
        }
    }
     /**
     * Register property at remote publisher.
     * @param property  property to be registered
     */
    @Override
    public void register(String property) throws RemoteException {
        if (connected) {
                // Nothing changes at remote publisher in case property was already registered
                publisherForDomain.registerProperty(property);
            
        }
    }
        /**
     * Subscribe to property.
     * @param property property to subscribe to
     */
    @Override
    public void subscribe(String property) throws RemoteException{
        if (connected) {
            final IRemotePropertyListener listener = this;
              threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForListener.subscribeRemoteListener(listener, property);
                        } 
                    catch (RemoteException ex) {
                        Logger.getLogger(SpelCommunicator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            });
 

        }
    }
        /**
     * Unsubscribe to property.
     * @param property property to unsubscribe to
     */
    @Override
    public void unsubscribe(String property) throws RemoteException {
        if (connected) {
            final IRemotePropertyListener listener = this;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForListener.unsubscribeRemoteListener(listener, property);
                    } catch (RemoteException ex) {
                        Logger.getLogger(SpelCommunicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
    /**
     * Broadcast draw event.
     * @param property  color of draw event
     * @param drawEvent draw event
     */
    public void broadcast(String property, ZetEvent event) {
        if (connected) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForDomain.inform(property,null,event);
                        
                    } catch (RemoteException ex) {
                        Logger.getLogger(SpelCommunicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });



            
        }
    }
                     

    @Override
    public void propertyChange(PropertyChangeEvent pce) throws RemoteException {
        String property = pce.getPropertyName();
        if(property.contains("opgeven")){
            controller.tegenstanderGeeftOp();
        }
        else{
            controller.ontvangZet((ZetEvent)pce.getNewValue());
        }
        

                
            
    }

    @Override
    public boolean ZetDoen(int id, String kleur, ZetEvent zet) throws RemoteException {
        broadcast(id + ", " + kleur, zet);
        return true;
    }
    @Override 
    public boolean Opgeven(int id, String kleur, ZetEvent zet){
        broadcast(id + ", " + "opgeven" + kleur, zet);
        return true;
        
    }
}
