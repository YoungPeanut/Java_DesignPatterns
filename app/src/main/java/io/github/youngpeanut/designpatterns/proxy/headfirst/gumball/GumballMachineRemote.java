package io.github.youngpeanut.designpatterns.proxy.headfirst.gumball;

import android.os.RemoteException;
 
public interface GumballMachineRemote
	/*	extends Remote */
{
	public int getCount() throws RemoteException;
	public String getLocation() throws RemoteException;
	public State getState() throws RemoteException;
}
