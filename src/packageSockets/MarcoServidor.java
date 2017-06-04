package packageSockets;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MarcoServidor extends JFrame {

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

	}

	private JTextArea areatexto;
}
