public class Cliente {
	public static void main(String[] args){
//		Conectar conecta = new Conectar("189.79.55.219",5050);
		Conectar conecta = new Conectar("localhost",5050);
		if(conecta.getSock().isConnected()){
			Processar pro = new Processar(conecta);
	        Thread threadProcessar = new Thread(pro);
	        threadProcessar.start();
        }
   }
} 