package Interfaz;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EventoGenerarCmd implements ActionListener{
	
	private String comando;
	JTextArea textAreaSalida;
	JPanel cp;
	JTextField path;

	public EventoGenerarCmd(String cmd, JTextArea refTextArea, JPanel contentPane, Component component) {
			this.comando = cmd;
			textAreaSalida = refTextArea;
			cp = contentPane;
			path = (JTextField) component;
	}	 

	@Override
	public void actionPerformed(ActionEvent e) {
		String cd = "cmd /c "+ path.getText();
		//System.out.println(cd);
		Runtime cmd = Runtime.getRuntime();
		String linea = null;
		try
		{
			//Process process = cmd.exec("cmd.exe");
			//Process process = cmd.exec("cmd.exe /c start cmd");
			//Process process = cmd.exec("cmd.exe /c start cd");
			//Process process = cmd.exec("cmd.exe /c c:\\Usuarios\\Santiago\\desktop");
			Process process= cmd.exec(comando);
			process = cmd.exec("exit");
			//cmd.exit(2);
			
			InputStreamReader entrada = new InputStreamReader(process.getInputStream());
			BufferedReader read = new BufferedReader(entrada);
			
			textAreaSalida.setText("");
			
			while((linea = read.readLine()) != null){
				textAreaSalida.append(linea+"\n");
				cp.updateUI();
			}
			
            //Si el comando tiene una salida la mostramos
           /* if((linea=read.readLine()) != null){
                System.out.println("Comando ejecutado Correctamente");
                while ((linea=read.readLine()) != null){
                    System.out.println(linea);
                }
            }else{
                System.out.println("No se a producido ninguna salida");
            }*/
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	//@Override
	public void actionPerformed2(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Comando final: " + comando);
		System.out.println("Comando final: " + comando);
	}
}