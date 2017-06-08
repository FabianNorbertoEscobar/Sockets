package packageSockets;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * La clase EnvioOnline envía una señal online
 * @author Escobar
 *
 */
public class EnvioOnline extends WindowAdapter {

	public void WindowOpened(WindowEvent e) {
		
		try {
			// creamos un socket
			Socket miSocket = new Socket("192.168.100.185", 9999);
			
			// creamos un paquete con el mensaje online
			PaqueteEnvio datos = new PaqueteEnvio();
			datos.setMsg(" online");
			
			// creamos  un flujo de salida conectado con el fujo de salida del socket
			ObjectOutputStream paqueteDatos = new ObjectOutputStream(miSocket.getOutputStream());
			// escribimos el paquete a enviar en el flujo
			paqueteDatos.writeObject(datos);
			// cerramos el flujo de salida
			paqueteDatos.close();
			
			// cerramos el Socket
			miSocket.close();
			
		} catch (Exception e2) {
			
		}
	}
}
