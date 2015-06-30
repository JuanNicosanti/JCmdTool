package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import EstructuraXML.Aplicacion;

public class EventoContinuar implements ActionListener {

	private VentanaSwing ventana;
	JPanel cp;
	
	public EventoContinuar(VentanaSwing ventanaSwing, JPanel contentPane) {
		ventana = ventanaSwing;
		cp = contentPane;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(ventana.tieneParametrosValidos()){
			ventana.agregarCuadroPath();
			ventana.generarBotonCmd();
		}
		else {
			cp.remove(ventana.getRefBotonComando());
			return;
		}

	}

}