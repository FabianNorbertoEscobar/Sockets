package packageSockets;

import javax.swing.*;

@SuppressWarnings("serial")
public class MarcoCliente extends JFrame {

	public MarcoCliente() {
		setResizable(false);
		setTitle("Cliente");

		setBounds(600, 300, 487, 350);

		LaminaMarcoCliente milamina = new LaminaMarcoCliente();

		getContentPane().add(milamina);

		setVisible(true);
	}
}
