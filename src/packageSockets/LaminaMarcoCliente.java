package packageSockets;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LaminaMarcoCliente extends JPanel implements Runnable {

	public LaminaMarcoCliente() {

		nick = new JTextField(5);
		add(nick);

		JLabel texto = new JLabel("CHAT");
		add(texto);

		ip = new JTextField(8);
		add(ip);

		campoChat = new JTextArea(12, 20);
		add(campoChat);

		campo1 = new JTextField(20);
		add(campo1);

		miboton = new JButton("Enviar");
		miboton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				campoChat.append("\n" + campo1.getText());
				try {
					// se crea un socket con la ip del servidor y el puerto por
					// el que nos vamos a comunicar
					Socket socket = new Socket("192.168.100.185", 9999);
					
					// creamos un paquete de env�o
					PaqueteEnvio paquete = new PaqueteEnvio();
					// empaquetamos los datos a enviar
					paquete.setNick(nick.getText());
					paquete.setIp(ip.getText());
					paquete.setMsg(campo1.getText());
					
					// creamos un flujo de salida para objetos con el flujo de salida del socket
					ObjectOutputStream paqueteDatos = new ObjectOutputStream(socket.getOutputStream());
					// escribimos el paquete en el flujo
					paqueteDatos.writeObject(paquete);
					// se cierra el socket
					socket.close();
					
					/*
					 * // se crea un flujo de salida con el flujo de salida del
					 * // socket DataOutputStream flujoSalida = new
					 * DataOutputStream(socket.getOutputStream());
					 * 
					 * // se escribe en el flujo el contenido del textField
					 * flujoSalida.writeUTF(campo1.getText());
					 * 
					 * // cerramos el flujo flujoSalida.close();
					 */
					campo1.setText("");
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		});

		add(miboton);

		// instanciamos un nuevo hilo de ejecuci�n y lo arrancamos
		Thread hilo = new Thread(this);
		hilo.start();
	}
	
	@SuppressWarnings("resource")
	@Override
	public void run() {
		try {
			// el cliente tambi�n necesita un 
			ServerSocket servidorCliente = new ServerSocket(9090);
			
			// variables para almacenar el socket y el paquete que nos env�a el servidor
			Socket socketCliente;
			PaqueteEnvio paqueteRecibido;
			
			// el socket se queda a la escucha de forma infinita
			while (true) {
				// se aceptan conexiones del servidor
				socketCliente = servidorCliente.accept();
				// se crea un flujo de entrada con el flujo de entrada del socket
				ObjectInputStream flujoEntrada = new ObjectInputStream(socketCliente.getInputStream());
				// se carga el paquete recibido desde el flujo de entrada
				paqueteRecibido = (PaqueteEnvio) flujoEntrada.readObject();
				
				// se concatenan los datos del paquete en el textArea
				campoChat.append("\n" + paqueteRecibido.getNick() + " " + paqueteRecibido.getMsg());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private JTextField campo1;
	private JTextField nick;
	private JTextField ip;
	private JTextArea campoChat;
	private JButton miboton;
}
