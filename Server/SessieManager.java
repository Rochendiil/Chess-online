/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interfaces.ISessieManager;
import Spel.Spel;
import Spel.Speler;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Luuk
 */
public class SessieManager extends UnicastRemoteObject implements ISessieManager {

    private ServerMain server;
    public SessieManager(ServerMain server) throws RemoteException  {
        this.server = server;
    }

    
    @Override
    public void StartSpel(Spel newspel) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startQueue(Speler speler) throws RemoteException {
        server.addSpelerToQueue(speler);
    }

    @Override
    public String Refresh(Speler speler) throws RemoteException {
            
            String returnstring = "";
            for (int i = 0; i < server.getSpellijst().size(); i++) {
                Spel spel = server.getSpellijst().get(i);
                if(spel.getSpeler1().getNaam().equals(speler.getNaam())){
                    returnstring += spel.getId() + ",wit," + spel.getSpeler2().getNaam();
                    return returnstring;
                }
                if(spel.getSpeler2().getNaam().equals(speler.getNaam())){
                    returnstring += spel.getId() + ",zwart," + spel.getSpeler1().getNaam();
                    return returnstring;
                }
            }
            return null;
        

    }

    @Override
    public void StopSpel(int spelid) throws RemoteException {
        for (int i = 0; i < server.getSpellijst().size(); i++) {
            Spel spelserver = server.getSpellijst().get(i);
                if(spelid == spelserver.getId()){
                    server.getSpellijst().remove(i);
                    System.out.println("spelid: " + i + " is gestopped");
                }
        }
    }
    
}
