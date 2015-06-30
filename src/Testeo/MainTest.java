package Testeo;

import java.util.ArrayList;
import java.util.List;

import EstructuraXML.Aplicacion;
import EstructuraXML.SubAplicacion;
import Parser.CPoolXMLHandler;
import Parser.Parser;

public class MainTest {
	public static void main(String[] args) 
	{
		Aplicacion git = new Aplicacion();
		List<SubAplicacion> subApps = new ArrayList<SubAplicacion>();
		
		Parser parser = new Parser();
		CPoolXMLHandler handler = new CPoolXMLHandler();
		parser.setHandler(handler);
		
	    parser.parsearXml("algoritmos2_tp.xml");
	    
	    
	    List<Aplicacion> lst = handler.getContenidoXml();
	    
	    git = lst.get(3);
	    subApps = git.getSubAplicacioes();
	    
	    for(int i = 0; i<subApps.size(); i++){
		    System.out.print(git.getValor() + " " + subApps.get(i).getValor() + " ");
	    	for(int j = 0; j<subApps.get(i).getParametros().size(); j++){
	    		System.out.print(subApps.get(i).getParametros().get(j).getValor() + " ");
	    	}
	    	System.out.println();
	    }
		
	}
}
