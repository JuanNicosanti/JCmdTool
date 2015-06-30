package EstructuraXML;

import java.util.ArrayList;
import java.util.List;

public class Aplicacion extends Tag {
	List<SubAplicacion> subAplicaciones = new ArrayList<SubAplicacion>();

	public void agregarSubApp(SubAplicacion subAplicacion) {
		subAplicaciones.add(subAplicacion);	
	}

	public List<SubAplicacion> getSubAplicacioes() {
		return this.subAplicaciones;
	}

}