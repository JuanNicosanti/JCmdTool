package Parser;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import EstructuraXML.Aplicacion;
import EstructuraXML.Parametro;
import EstructuraXML.SubAplicacion;

public class CPoolXMLHandler extends DefaultHandler {
	
	List<Aplicacion> contenidoXml = new ArrayList<Aplicacion>();
	
	public List<Aplicacion> getContenidoXml() {
		return contenidoXml;
	}

	public void setContenidoXml(List<Aplicacion> contenidoXml) {
		this.contenidoXml = contenidoXml;
	}

	Aplicacion aplicacion;
	SubAplicacion subAplicacion;
	Parametro parametro;
	
	public void startElement(String uri,
			String localName,
			String qName,
			Attributes attributes)
	{

		//System.out.println("Abre Tag: "+qName);
		
		if(qName == "Parametro"){
			
			parametro = new Parametro();
			for(int i=0; i<attributes.getLength(); i++) // Persistencia
			{
				parametro.setPrefijo(attributes.getValue(i));
				
				if(attributes.getQName(i+1) == "contenido") {
					parametro.setValor(attributes.getValue(i+1));
					i++;
				}
				
				if(attributes.getQName(i+1) == "tipo") {
					parametro.setTipoValidacion(attributes.getValue(i+1));
					i++;
				}
				
				if(attributes.getQName(i+1) == "cotaMin") {
					int aux = Integer.parseInt(attributes.getValue(i+1));
					parametro.setCotaMin(aux);
					i++;
				}
				
				if(attributes.getQName(i+1) == "cotaMax") {
					int aux = Integer.parseInt(attributes.getValue(i+1));
					parametro.setCotaMax(aux);
					i++;
				}
			}
	
		}
		if(qName == "SubApp"){
			subAplicacion = new SubAplicacion();
			for(int i=0; i<attributes.getLength(); i++) // Persistencia
			{
				//tagCompuesto.setNombre(attributes.getQName(i));
				subAplicacion.setValor(attributes.getValue(i));
				/*if(attributes.getQName(i+1) == "usoReal") {
					subAplicacion.setUsoReal(attributes.getValue(i+1));
					i++;
				}*/
			}
		}
		if(qName == "Aplicacion"){
			aplicacion = new Aplicacion();
			for(int i=0; i<attributes.getLength(); i++) // Persistencia
			{
				//tagCompuesto.setNombre(attributes.getQName(i));
				aplicacion.setValor(attributes.getValue(i));
			}
		}
		
		switch(qName){
			case "Aplicacion":
				aplicacion.setTipoTag(qName);
				contenidoXml.add(aplicacion);
				break;
			case "SubApp":
				subAplicacion.setTipoTag(qName);
				aplicacion.agregarSubApp(subAplicacion);
				break;
			case "Parametro":
				parametro.setTipoTag(qName);
				subAplicacion.agregarParametro(parametro);
				break;
		}
		// La idea es descartar el Tag "Aplicaciones" ya que no nos aporta nada, y guardar en contenidoXml un registro por cada Aplicacion
		// A partir de ahí, dependiendo del tipo de Tag de que se trate lo persistiremos de una u otra manera
		// Si es un parametro, lo agrego como subTag en el último tagCompuesto que parsee, que puede ser una Aplicacion (si no tiene SubApps) o una SubApp
		// Si es una SubApp, lo agrego como subTag en el último registro de contenidoXml, ya que este guarda sólo Aplicaciones y la última corresponderá a la App donde este está enmarcado
	}

	//public void endElement(String uri, String localName, String qName)
	//{
	//	System.out.println("Cierra Tag: "+qName);
	//}
}