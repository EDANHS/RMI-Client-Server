package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import common.InterfazDeServer;
import common.Persona;

public class Client {
	private InterfazDeServer server;
	
	public void startClient() throws RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry("localhost", 1099);
		server = (InterfazDeServer) registry.lookup("severDePersonas");
		System.out.println("Cliente conectando");
	}
	
	public ArrayList<Persona> getPersonas() throws RemoteException{
		return server.getPersonas();
	}
	
	public void mostrarPersonas() throws RemoteException {
		ArrayList<Persona> list = this.getPersonas();
		for(int idx = 0; idx < list.size(); idx++) {
			System.out.println("Nombre de la persona: " + list.get(idx).getNombre());
			System.out.println("Edad persona: " + list.get(idx).getEdad());
		}
	}
	
	public void crearPersonaYEnviarAServidor(String nombre, int edad) throws RemoteException {
		server.Persona(nombre, edad);
	}
	
}
