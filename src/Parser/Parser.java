package Parser;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Parser{
	
	static CPoolXMLHandler handler = new CPoolXMLHandler();
	
	public CPoolXMLHandler getHandler() {
		return handler;
	}

	public void setHandler(CPoolXMLHandler handler) {
		Parser.handler = handler;
	}

	public static void main (String[] args){
		try
		{
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
				
			sp.parse(args[0], handler);
			// Setear en Run > RunConfigurations > Arguments > "algoritmos2_tp.xml"
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	
	public void parsearXml (String xml){
		try
		{
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
				
			sp.parse(xml, handler);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
