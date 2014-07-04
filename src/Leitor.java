import java.io.IOException;
import java.util.Scanner;

public class Leitor{
	
	private Conectar sock;
	private Scanner entrada;
	
	public Leitor(Conectar sock){
		this.sock = sock;
		try{
			this.entrada = new Scanner(this.sock.getSock().getInputStream()).useDelimiter("\0");
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public String ler(){
        if(this.sock.getSock().isConnected()){
    		String linha = "";
    		if(this.entrada.hasNext()){
    			linha = this.entrada.next();
    			return linha;							
    		}else{
    			return "";
    		}
        }
		return null;		
	}
}