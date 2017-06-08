package packageSockets;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LaminaMarcoCliente extends JPanel implements Runnable {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LaminaMarcoCliente() {
		
		String nickUsuario = JOptionPane.showInputDialog("Nick: ");
		
		JLabel n_nick = new JLabel("Nick: ");
		add(n_nick);

		nick = new JLabel();
		nick.setText(nickUsuario);
		add(nick);

		JLabel texto = new JLabel("Online: ");
		add(texto);

		ip = new JComboBox();
		ip.addItem("Usuario 1");
		ip.addItem("Usuario 2");
		ip.addItem("Usuario 3");
		ip.addItem("Usuario 4");
		ip.addItem("Usuario 5");
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
					
					// creamos un paquete de envío
					PaqueteEnvio paquete = new PaqueteEnvio();
					// empaquetamos los datos a enviar
					paquete.setNick(nick.getText());
					paquete.setIp(ip.getSelectedItem().toString());
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

		// instanciamos un nuevo hilo de ejecución y lo arrancamos
		Thread hilo = new Thread(this);
		hilo.start();
	}
	
	@SuppressWarnings({ "resource", "unchecked" })
	@Override
	public void run() {
		try {
			// el cliente también necesita un 
			ServerSocket servidorCliente = new ServerSocket(9090);
			
			// variables para almacenar el socket y el paquete que nos envía el servidor
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
				
				if (!paqueteRecibido.getMsg().equals(" online")) {
					// se concatenan los datos del paquete en el textArea
					campoChat.append("\n" + paqueteRecibido.getNick() + " " + paqueteRecibido.getMsg());
				} else {
					// campoChat.append("\n" + paqueteRecibido.getIps());
					
					// creamos una lista con las ip recibidas del mensaje
					ArrayList<String> ipsMenu = new ArrayList<String>();
					ipsMenu = paqueteRecibido.getIps();
					
					// borra todos los ítems al menu de las ip
					ip.removeAllItems();
					
					// carga en los ítems del menú la lista de ip actualizada
					for (String z: ipsMenu) {
						ip.addItem(z);
					}
				}
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private JTextField campo1;
	private JLabel nick;
	@SuppressWarnings("rawtypes")
	private JComboBox ip;
	private JTextArea campoChat;
	private JButton miboton;
}
