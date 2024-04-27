package server;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.InterfazDeServer;
import common.Persona;

public class RunServer {
	public static void main(String args[]) throws NotBoundException, NumberFormatException, IOException {
		InterfazDeServer server = new ServerImpl();
		//Lista de objetos que el cliente puede acceder
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("severDePersonas", server);
		System.out.println("Servidor Arriba");
		mostrar_menu(server);
		cerrarServer(server);
	}
	
	public static void mostrar_menu(InterfazDeServer server) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do {
            System.out.println("***********Menú Server: ********");
            System.out.println("[1] Agregar dato");
            System.out.println("[2] Mostrar datos");
            System.out.println("[3] Salir");
            System.out.print("Ingrese su opción: ");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1:
                    agregarDato(br, server);
                    break;
                case 2:
                    mostrarDatos(server);
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 3);

       br.close();
    }
	
	public static void agregarDato(BufferedReader br, InterfazDeServer server) throws IOException {
		System.out.print("Ingrese el nombre de la persona: ");
        String nombre = br.readLine();
        System.out.print("Ingrese la edad de la persona: ");
        int edad = Integer.parseInt(br.readLine());
        
        server.Persona(nombre, edad);
	}
	
	public static void mostrarDatos(InterfazDeServer server) throws RemoteException {
		List<Persona> personas = server.getPersonas();
		System.out.println("*************************************\n");
		for (Persona p : personas) {
			
			System.out.println("Nombre: " + p.getNombre());
			System.out.println("Edad: " + p.getEdad());
			System.out.println();
		}
		System.out.println("*************************************");
	}
	
	@SuppressWarnings("unused")
	private static void cerrarServer(InterfazDeServer server) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(1099);
		registry.unbind("severDePersonas");
		UnicastRemoteObject.unexportObject(server, true);
		System.out.println("Cerrando servidor...");
	}
}

