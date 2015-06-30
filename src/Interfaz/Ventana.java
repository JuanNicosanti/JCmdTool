package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import EstructuraXML.Aplicacion;
import EstructuraXML.Parametro;
import EstructuraXML.SubAplicacion;
import Parser.CPoolXMLHandler;
import Parser.Parser;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Ventana extends JFrame {

	private JPanel contentPane;
	Parser parser = new Parser();
	CPoolXMLHandler handler = new CPoolXMLHandler();
	List<ValidadorParametro> validadores= new ArrayList<ValidadorParametro>();
	
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	JPanel panel_3 = new JPanel();
	JPanel panel_4 = new JPanel();
	JPanel panel_5 = new JPanel();
	JPanel panel_6 = new JPanel();
	String usoRealSubApp = new String("");
	String comandoParcial = new String("");
	JButton btnValidarCampos = new JButton("Validar campos");
	JButton btnGenerarComando = new JButton("Generar comando");
	JTextField textField = new JTextField();
	JTextArea textArea = new JTextArea();
	private JTextField textField_1;
	private final JButton btnEjecutar = new JButton("Ejecutar");
	JComboBox<String> comboBox = new JComboBox<String>();
	JComboBox<String> comboBox_1 = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		super("JCmdTool: Java Command Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		
		parser.setHandler(handler);
	    parser.parsearXml("algoritmos2_tp.xml");
	    List<Aplicacion> apps = new ArrayList<Aplicacion>();
	    apps.addAll(handler.getContenidoXml());
	    
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel.setBounds(10, 11, 397, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAplicaciones = new JLabel("Aplicaciones: ");
		lblAplicaciones.setBounds(10, 11, 101, 14);
		panel.add(lblAplicaciones);
		
		Vector<String> aplicaciones = new Vector<String>();
		for(int i = 0; i < apps.size(); i++){
			 aplicaciones.addElement(apps.get(i).getValor());
		}
		comboBox = new JComboBox<String>(aplicaciones);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarComandoParcial();
				Aplicacion appElegida = new Aplicacion();
				String nombreAppElegida = (String) comboBox.getSelectedItem();

				for(int i = 0; i < apps.size(); i++){
					if(apps.get(i).getValor() == nombreAppElegida)
						appElegida = apps.get(i);
				}
				 
				if(appElegida.getSubAplicacioes().size()>0){
					generarPanelSubapps(appElegida);
				}
				else return;
			}
		});
		comboBox.setBounds(132, 8, 255, 20);
		panel.add(comboBox);
		
		panel_1.setBounds(10, 43, 397, 49);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JLabel lblSubAplicaciones = new JLabel("Sub Aplicaciones: ");
		lblSubAplicaciones.setBounds(10, 23, 119, 14);
		panel_1.add(lblSubAplicaciones);
		
		panel_2.setBounds(10, 103, 397, 253);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		
		JLabel lblParametros = new JLabel("Parametros: ");
		lblParametros.setBounds(10, 11, 92, 14);
		panel_2.add(lblParametros);
		
		panel_3.setBounds(10, 36, 387, 217);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		btnValidarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tieneParametrosValidos()){
					btnGenerarComando.setVisible(true);
				}
			}
		});
		
		btnValidarCampos.setBounds(50, 367, 142, 23);
		btnValidarCampos.setVisible(false);
		contentPane.add(btnValidarCampos);
		btnGenerarComando.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.setVisible(true);
				textField.setText(generarCmd());
				panel_6.setVisible(true);
				btnEjecutar.setVisible(true);
			}
		});
		
		btnGenerarComando.setBounds(221, 367, 167, 23);
		btnGenerarComando.setVisible(false);
		contentPane.add(btnGenerarComando);
		
		panel_5.setBounds(10, 401, 397, 49);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setVisible(false);
		
		JLabel lblComando = new JLabel("Comando: ");
		lblComando.setBounds(10, 11, 89, 14);
		panel_5.add(lblComando);
		
		textField.setBounds(78, 8, 292, 20);
		panel_5.add(textField);
		textField.setColumns(10);
		
		panel_6.setBounds(10, 443, 397, 49);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		panel_6.setVisible(false);
		
		JLabel lblDirectorio = new JLabel("Directorio:");
		lblDirectorio.setBounds(10, 11, 81, 14);
		panel_6.add(lblDirectorio);
		
		textField_1 = new JTextField();
		textField_1.setBounds(78, 8, 292, 20);
		panel_6.add(textField_1);
		textField_1.setColumns(10);
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				Runtime cmd = Runtime.getRuntime();
				try
				{
					Process process = cmd.exec("cd" + textField_1.getText());
					process = cmd.exec(textField.getText());
					//process = cmd.exec("exit");
					
					BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
					
					textArea.setVisible(true);
					textArea.setText("");
					
					String linea;
					//String lectura;
					
					while((linea = read.readLine()) != null){
						textArea.append(linea+"\n");
						//System.out.println(linea+"\n");
					}
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEjecutar.setBounds(20, 503, 89, 37);
		btnEjecutar.setVisible(false);
		
		contentPane.add(btnEjecutar);
		
		textArea.setBounds(135, 503, 272, 48);
		contentPane.add(textArea);
		textArea.setVisible(false);
	}
	
	public void generarPanelSubapps(Aplicacion appElegida){
		panel_1.setVisible(true);
		panel_1.remove(comboBox_1);
		Vector<String> subApps = new Vector<String>();
		subApps.addElement("Elegir comando");
		for(int i = 0; i < appElegida.getSubAplicacioes().size(); i++){
				 subApps.addElement(appElegida.getSubAplicacioes().get(i).getValor());
		}
		comboBox_1 = new JComboBox<String>(subApps);
		comboBox_1.setBounds(132, 20, 255, 20);
		panel_1.add(comboBox_1);
		
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarComandoParcial();
				
				SubAplicacion subAppElegida = new SubAplicacion();
				String nombreSubAppElegida = (String) comboBox_1.getSelectedItem();
				
				for(int i = 0; i < appElegida.getSubAplicacioes().size(); i++){
					if(appElegida.getSubAplicacioes().get(i).getValor() == nombreSubAppElegida)
						subAppElegida = appElegida.getSubAplicacioes().get(i);
				}
				generarPanelParametros(subAppElegida, nombreSubAppElegida);
			}
		});
	}
	
	public void generarPanelParametros(SubAplicacion subAppElegida, String nombreSubAppElegida){
		panel_3.removeAll();
		panel_2.setVisible(true);
		panel_3.setVisible(true);
		usoRealSubApp = subAppElegida.getUsoReal();
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros = subAppElegida.getParametros();
		
		String auxS;
		int auxInt = 31;
		
		for(int i = 0; i < parametros.size(); i++){
			auxS = parametros.get(i).getValor();
			auxInt = auxInt + 25;
			JLabel labelAux = new JLabel(auxS);
			labelAux.setBounds(15, auxInt, 145, 14);
			panel_3.add(labelAux);
			JTextField texto = new JTextField(20);
			texto.setBounds(175, auxInt, 204, 20);
			panel_3.add(texto);
			setJTexFieldChanged(texto);
			ValidadorParametro validador = new ValidadorParametro(texto, parametros.get(i));
			validadores.add(validador);
		}
		btnValidarCampos.setVisible(true);
		contentPane.updateUI();
	}
	
	public String generarCmd() {
		String comandoAux;
		comandoAux = (String) comboBox.getSelectedItem();
		comandoAux = comandoAux+" "+usoRealSubApp;
		contentPane.updateUI();
		return comandoAux+comandoParcial;
	}
	
	public boolean tieneParametrosValidos() {
		int i = 0;
		while(i < validadores.size()){
			if(validadores.get(i).tieneParametroValido() == false) return false;
			i++;
		}
		return true;
	}

	private void textoParametros() {
		this.reiniciarComandoParcial();
		int i = 0;
		while(i < validadores.size()){
			comandoParcial = comandoParcial+" "+(String) validadores.get(i).generarCmd();
			i++;
		}
		//System.out.println("Texto: "+comandoParcial);
	}
	
    private void setJTexFieldChanged(JTextField txt)
    {
        DocumentListener documentListener = new DocumentListener() {
 
        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent, txt);
        }
 
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent, txt);
        }
 
        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent, txt);
        }
        };
        txt.getDocument().addDocumentListener(documentListener);
 
    }
 
    private void printIt(DocumentEvent documentEvent, JTextField txt) {
        DocumentEvent.EventType type = documentEvent.getType();
 
        if (type.equals(DocumentEvent.EventType.CHANGE))
        {
        	//refBotonComando.removeAll();
        	this.textoParametros();
        	//System.out.println("Change: " + comandoParcial);
        	this.btnValidarCampos.getAction();
        }
        else if (type.equals(DocumentEvent.EventType.INSERT))
        {
        	//refBotonComando.removeAll();
        	this.textoParametros();
        	//System.out.println("Instert: " + comandoParcial);
        	this.btnValidarCampos.getAction();
        }
        else if (type.equals(DocumentEvent.EventType.REMOVE))
        {
        	//refBotonComando.removeAll();
        	this.textoParametros();
        	//System.out.println("Remove: " + comandoParcial);
        	this.btnValidarCampos.getAction();
        }
   }
    
    public void reiniciarComandoParcial() {
		comandoParcial = new String("");
	}
}
