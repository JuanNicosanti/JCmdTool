package Interfaz;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import EstructuraXML.Parametro;

public class ValidadorParametro {
	private JTextField texto;
	private Parametro parametro;
	
	public ValidadorParametro(JTextField unTextField, Parametro tag) {
		texto = unTextField;
		parametro = tag;
	}
	
	public JTextField getTexto(){
		return this.texto;
	}
	
	public Boolean validarCmd(){
		try {
			 return parametro.validarContenido(texto.getText());
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Parámetro "+parametro.getValor()+" no válido.");
			//e.printStackTrace();
		}
		return null;
	}
	
	public String generarCmd(){
		return parametro.getPrefijo()+" "+texto.getText();
	}

	public Boolean tieneParametroValido() {
		return parametro.validarContenido(texto.getText());	
	}
}