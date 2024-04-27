package server;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.InterfazDeServer;
import common.Persona;

public class ServerImpl implements InterfazDeServer{
	
	private ArrayList<Persona> database;
	
	public ServerImpl() throws RemoteException, FileNotFoundException {
		UnicastRemoteObject.exportObject(this, 0);
		this.database = new ArrayList<Persona>();
		dataInicial();
	}
	
	public void dataInicial() {
		this.database.add(new Persona("Aquiles Baeza",21));
		this.database.add(new Persona("Sacarias del Campo",20));
		this.database.add(new Persona("Rosa Espinosa",20));
	}

	@Override
	public ArrayList<Persona> getPersonas() throws RemoteException {
		return database;
	}

	@Override
	public void Persona(String nombre, int edad) throws RemoteException {
		Persona p = new Persona(nombre, edad);
		database.add(p);
	}
	
}
