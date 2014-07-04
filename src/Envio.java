import java.io.IOException;
import java.io.OutputStream;
import java.util.Formatter;

import javax.swing.JOptionPane;


public class Envio{

	private Conectar sock;
	private Formatter  saida;
	
	public Envio(Conectar sock){
        this.sock = sock;
		try {
			this.saida   = new Formatter(this.sock.getSock().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMsg(String msg){
		msg = msg+"\0"; 
		this.saida.format(msg);
		this.saida.flush();
	}
}
