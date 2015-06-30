package EstructuraXML;

import java.util.ArrayList;
import java.util.List;

public class SubAplicacion extends Tag {
	List<Parametro> parametros = new ArrayList<Parametro>();
	String usoReal;

	public String getUsoReal() {
		return usoReal;
	}

	public void setUsoReal(String usoReal) {
		this.usoReal = usoReal;
	}

	public void agregarParametro(Parametro parametro) {
		parametros.add(parametro);
	}

	public List<Parametro> getParametros() {
		return this.parametros;
	}

}