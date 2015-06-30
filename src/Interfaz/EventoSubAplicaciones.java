package Interfaz;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;

import EstructuraXML.Aplicacion;
import EstructuraXML.Tag;

public class EventoSubAplicaciones implements ItemListener {

	private VentanaSwing ventana;
	private JComboBox<String> comboBoxApps;
	private List<Aplicacion> apps;
	
	public EventoSubAplicaciones(VentanaSwing ventanaSwing, List<Aplicacion> unasApps, JComboBox<String> unComboBoxApps) {
		ventana = ventanaSwing;
		comboBoxApps = unComboBoxApps;
		apps = unasApps;

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		//Agarro la App elegida para usarla
		ventana.reiniciarComandoParcial();
		ventana.reiniciarValidadores();
		ventana.removerBotonComando();
		Aplicacion appElegida = new Aplicacion();
		String nombreAppElegida = (String) comboBoxApps.getSelectedItem();

		for(int i = 0; i < apps.size(); i++){
			if(apps.get(i).getValor() == nombreAppElegida)
				appElegida = apps.get(i);
		}
		 
		if(appElegida.getSubAplicacioes().size()>0){
			 ventana.generarPanelSubAplicaciones(appElegida);	
		}
		else return;
	}

}