package EstructuraXML;

import java.util.List;

public abstract class Tag {
	
	String tipoTag;
	String valor;

	public String getTipoTag(){
		return this.tipoTag;
	}
	
	public void setTipoTag(String unNombre){
		this.tipoTag = unNombre;
	}
	
	public String getValor(){
		return this.valor;
	}
	
	public void setValor(String unValor){
		this.valor = unValor;
	}
	
}