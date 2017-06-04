package packageSockets;

import javax.swing.*;

@SuppressWarnings("serial")
public class LaminaMarcoCliente extends JPanel {

	public LaminaMarcoCliente(){
		
		JLabel texto=new JLabel("CLIENTE");
		
		add(texto);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		
		add(miboton);	
		
	}
	
	private JTextField campo1;
	
	private JButton miboton;
}
