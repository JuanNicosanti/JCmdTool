package Interfaz;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import EstructuraXML.Aplicacion;
import EstructuraXML.SubAplicacion;
import EstructuraXML.Tag;

public class EventoParametros implements ItemListener {

	private VentanaSwing ventana;
	private JComboBox<String> comboBoxApps;
	private Aplicacion appElegida;
	
	public EventoParametros(VentanaSwing ventanaSwing, Aplicacion unaAppElegida, JComboBox<String> unComboBoxApps) {
		ventana = ventanaSwing;
		comboBoxApps = unComboBoxApps;
		appElegida = unaAppElegida;

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		//Agarro la App elegida para usarla
		ventana.reiniciarComandoParcial();
		ventana.reiniciarValidadores();
		ventana.removerBotonComando();
		SubAplicacion subAppElegida = new SubAplicacion();
		String nombreSubAppElegida = (String) comboBoxApps.getSelectedItem();
		
		for(int i = 0; i < appElegida.getSubAplicacioes().size(); i++){
			if(appElegida.getSubAplicacioes().get(i).getValor() == nombreSubAppElegida)
				subAppElegida = appElegida.getSubAplicacioes().get(i);
		}
		
		ventana.generarPanelParametros(subAppElegida, nombreSubAppElegida);
		
		 
	}

}