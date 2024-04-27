package client;
import java.util.List;
import java.util.Scanner;

import common.Persona;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunClient {
	public static void main(String args[]) throws RemoteException, NotBoundException {
		Client client = new Client();
		client.startClient();
		menu(client);
	}
	
	public static void menu(Client client) throws RemoteException{
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n******* Menú Cliente *******");
            System.out.println("1. Buscar persona por nombre");
            System.out.println("2. Agregar persona");
            System.out.println("3. Mostrar todas las personas");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre de la persona: ");
                    String nomb = scanner.nextLine();
                    
                    Persona p = buscarPersona(nomb, client.getPersonas());
                    if (p != null) {
                    	System.out.println("******************************");
                    	System.out.println("Persona encontrada ");
                    	System.out.println("Nombre: " + p.getNombre());
                    	System.out.println("Edad: " + p.getEdad());
                    	System.out.println("******************************");
                    }else {
                    	System.out.println("No existe una persona con ese nombre...");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de la persona: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la edad de la persona: ");
                    int edad = scanner.nextInt();
                    client.crearPersonaYEnviarAServidor(nombre, edad);
                    break;
                case 3:
                	client.mostrarPersonas();
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número válido.");
            }
        } while (opcion != 4);

        scanner.close();
    }
	
	public static Persona buscarPersona(String n, List<Persona> l) {
		for(int i = 0 ; i<l.size() ; i++) {
			if(l.get(i).getNombre().equals(n)) {
				return l.get(i);
			}
		}
		return null;
	}
}
