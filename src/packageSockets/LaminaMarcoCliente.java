package packageSockets;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LaminaMarcoCliente extends JPanel {

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
				try {
					// se crea un socket con la ip del servidor y el puerto por
					// el que nos vamos a comunicar
					Socket socket = new Socket("192.168.100.185", 9999);
					
					// creamos un paquete de envío
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

	}

	private JTextField campo1;
	private JTextField nick;
	private JTextField ip;
	private JTextArea campoChat;
	private JButton miboton;
}
