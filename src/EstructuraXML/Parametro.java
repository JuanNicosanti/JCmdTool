package EstructuraXML;

import javax.swing.JOptionPane;

public class Parametro extends Tag {
	String tipoValidacion; // Indica si debe ser solicitado al usuario o ya se esta proveyendo
	int cotaMin = -9999; // Mínimo de caracteres a ingresar en un determinado Tag
	int cotaMax = -9999; // Máximo de caracteres a ingresar en un determinado Tag
	String prefijo;
	
	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}
	
	public String getTipoValidacion(){
		return this.tipoValidacion;
	}

	public void setTipoValidacion(String unString){
		this.tipoValidacion = unString;
	}
	
	public int getCotaMin() {
		return cotaMin;
	}

	public void setCotaMin(int cotaMin) {
		this.cotaMin = cotaMin;
	}

	public int getCotaMax() {
		return cotaMax;
	}

	public void setCotaMax(int cotaMax) {
		this.cotaMax = cotaMax;
	}
	
	public Boolean validarContenido(String unValor){
		//System.out.println(unValor.length());
		if((unValor.length() >= cotaMin && unValor.length() <= cotaMax) || cotaMin == cotaMax){
			if(tipoValidacion != "obligatorio") return true;
			else;
			if(unValor.length() == 0) JOptionPane.showMessageDialog(null, "El parámetro "+this.getValor()+" no puede ser nulo.");
			else return true;
		}
		else JOptionPane.showMessageDialog(null, "Parámetro "+this.getValor()+" no válido.");
			//throw new RuntimeException("El valor ingresado no es válido");
		return null;
	}
}