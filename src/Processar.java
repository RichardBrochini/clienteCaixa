import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Processar implements Runnable {
	private Conectar sock;
	private Leitor leitor;
    private Envio  envio;
    private int[] numero = {0,0,0,0,0,0};
    
    public Processar(Conectar conecta){
    	this.sock   = conecta; 
    	this.leitor = new Leitor(conecta);
        this.envio  = new Envio(conecta);    	
		this.envio.enviarMsg("0");
		this.setNumeros(this.leitor.ler());
    }
    
    public void setNumeros(String num){
    	for(int i=0;i<6;i++){
        	this.numero[i] = Integer.parseInt(num.split(",")[i]);    		
    	}
    }
    
    public String contagem(String str){
	    System.out.println(str);
    	String resultado;
	    String[] vetor;
	    int quina = 0,quadra = 0,sena = 0;	        
	    int acertos  = 0;
	    int verifica = 2;
	    vetor = new String[str.split(",").length];
	    vetor = str.split(",");
	    for(int j = 4 , k = (vetor.length-1);j<vetor.length;j++,k--){	            		
	    	while(verifica<7){
	    		if(this.numero[verifica-2]==Integer.parseInt(vetor[j])){
	    			acertos++;
	        	}
	        	if(acertos>=6){
	        		break;
	        	}
	    		if(this.numero[verifica-1]==Integer.parseInt(vetor[k])){
	    			acertos++;
	        	}
	        	if(acertos>=6){
	        		break;
	        	}
	    		verifica = verifica +2;
	    	}
	    	verifica = 2;
	    }
		switch (acertos) {
		case 4:
			quadra++;
			break;
		case 5:
			quina++;
			quadra = 5;
			break;
		case 6:
			sena++;
			quina  = 6;
			quadra = 15;
			break;
		}
		if(sena>0){
		    System.out.println("sena   = "+sena);	            				
		}
		if(quina>0){
			System.out.println("quina  = "+quina);	            	
		}
		if(quadra>0){
			System.out.println("quadra = "+quadra);	            	
		}
	    resultado = String.valueOf(sena)+";"+str.split(",")[2]+","+String.valueOf(quina)+","+String.valueOf(quadra)+","+str.split(",")[0];
		quina    = 0;
	    quadra   = 0;
	    sena     = 0;	        
	    acertos  = 0;
	    verifica = 2;
	    return resultado;
    }
    
	public void run() {
		boolean enviar = true;
		String tmp;
		while(this.sock.getSock().isConnected()){
			this.envio.enviarMsg("1");
			tmp = this.leitor.ler();
			if(tmp.equals("acabou")){
				if(enviar){
					this.envio.enviarMsg("10");
					enviar = false;
				}
			}
			if(tmp.length()<10){
			
			}else{
				try{
					this.envio.enviarMsg(contagem(tmp));					
				}catch(Exception e){
					
				}
			}
		}
	}
}
