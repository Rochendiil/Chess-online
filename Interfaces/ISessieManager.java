/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Spel.Spel;
import Spel.Speler;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Luuk
 */
public interface ISessieManager extends Remote{
    public abstract void StartSpel(Spel newspel) throws RemoteException;
    public abstract void startQueue(Speler speler) throws RemoteException;
    public abstract String Refresh(Speler speler) throws RemoteException;
    public abstract void StopSpel(int spelid) throws RemoteException;
}
