package packageSockets;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

@SuppressWarnings("serial")
public class MarcoServidor extends JFrame implements Runnable {

	public MarcoServidor() {
		setResizable(false);
		setTitle("Servidor");

		setBounds(1200, 300, 488, 350);

		JPanel milamina = new JPanel();

		milamina.setLayout(new BorderLayout());

		areatexto = new JTextArea();

		milamina.add(areatexto, BorderLayout.CENTER);

		getContentPane().add(milamina);

		setVisible(true);

		Thread hilo = new Thread(this);
		hilo.start();
	}

	private JTextArea areatexto;

	@Override
	public void run() {
		try {
			// se crea un socket servidor que quedará a la escucha por el puerto
			// 9999
			ServerSocket servidor = new ServerSocket(9999);

			// se aceptarán conexiones de forma infinita
			while (true) {
				// se crea un socket en el que se aceptan conexiones de clientes
				// al servidor
				Socket server = servidor.accept();

				// variable para leer los datos recibidos
				String nick, ip, msg;

				// se instancia un nuevo paquete donde recibiré el paquete que
				// me envían
				PaqueteEnvio paquete;

				// se crea un flujo de entrada para objeto con el flujo de
				// entrada del socket
				ObjectInputStream paqueteDatos = new ObjectInputStream(server.getInputStream());

				// se guarda en el packete el objeto que tiene el flujo de
				// entrada
				paquete = (PaqueteEnvio) paqueteDatos.readObject();

				// con los datos del paquete se cargan las variables
				nick = paquete.getNick();
				ip = paquete.getIp();
				msg = paquete.getMsg();

				/*
				 * // se crea un flujo de entrada con el flujo de entrada del
				 * socket server DataInputStream flujoEntrada = new
				 * DataInputStream(server.getInputStream());
				 * 
				 * // se lee el contenido del flujo String msg =
				 * flujoEntrada.readUTF();
				 * 
				 * // se concatena el msg en el textArea areatexto.append("\n" +
				 * msg);
				 * 
				 */

				// se concatena el mensaje en el area de texto, con todos los
				// datos recibidos
				areatexto.append("\n" + nick + ": " + msg + " para " + ip);

				// se cierra el socket
				server.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
