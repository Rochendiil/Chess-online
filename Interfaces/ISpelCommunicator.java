/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Spel.Spel;
import Spel.Speler;
import Spel.Vak;
import Spel.ZetEvent;
import Stuk.SchaakStuk;
import fontyspublisher.IRemotePropertyListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Luuk
 */
public interface ISpelCommunicator extends Remote, IRemotePropertyListener{
    public boolean ZetDoen(int id, String kleur, ZetEvent zet) throws RemoteException;
    public void subscribe(String property) throws RemoteException;
    public void unsubscribe(String property) throws RemoteException;
    public void register(String property) throws RemoteException; 
    public boolean Opgeven(int id, String kleur, ZetEvent zet) throws RemoteException;
}
