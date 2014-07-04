import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Conectar {
	private Socket sock;
	private String host;
	private int porta;
	
	public Conectar(String host,int porta){
		this.setHost(host);
		this.setPorta(porta);
		try {
			this.sock = new Socket(this.getHost(),this.getPorta());
		} catch (UnknownHostException e){
			e.printStackTrace();
        	JOptionPane.showMessageDialog(null,"Conexão feita: "+e.getMessage());
		} catch (IOException e) {
        	JOptionPane.showMessageDialog(null,"Conexão não realizada: "+e.getMessage());
        	System.exit(0);
		}
	}
	
	public void Fechar(){
		try {
			this.getSock().close();
			this.getSock().getInputStream().close();
			this.getSock().getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Socket getSock(){
		return this.sock;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
}
