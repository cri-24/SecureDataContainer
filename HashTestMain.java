
public class HashTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecureDataContainer<String> mydata = new MyHashSecureDataContainer<String>();
		
		//inserisco 3 utenti: <user 1,Passw 1>,<user 2,Passw 2>,<user 3,Passw 3>
		try { 
			mydata.createUser("User 1" , "Pass 1");
			System.out.println("User 1 Pass 1");
			mydata.createUser("User 2" , "Pass 2");
			System.out.println("User 2 Pass 2");
			mydata.createUser("User 3" , "Pass 3");
			System.out.println("User 3 Pass 3");
		} 
		catch ( NameAlreadyExistsException e1) { System.err.println("Nome già esistente!");}
		catch ( NullPointerException e2) { System.err.println("Hai inserito un parametro nullo!");}
		
		
		//inserisco {d1,d2,d3} in User 1
		try { 
			System.out.println("Esito inserzione User 1:" + mydata.put("User 1", "Pass 1", "d1"));
			System.out.println("Esito inserzione User 1:" + mydata.put("User 1", "Pass 1", "d2"));
			System.out.println("Esito inserzione User 1:" + mydata.put("User 1", "Pass 1", "d3"));
		}
		catch (NullPointerException e5) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e6) { System.err.println("Non sei registrato!");}
		
		//inserisco {d1,d4} in User 3
		try { 
			System.out.println("Esito inserzione User 3:" + mydata.put("User 3", "Pass 3", "d1"));
			System.out.println("Esito inserzione User 3:" + mydata.put("User 3", "Pass 3", "d4"));}
		catch (NullPointerException e7) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e8) { System.err.println("Non sei registrato!");}
		
		
		try { //ci sono 3 elementi in User 1, 0 in User2 e 2 elementi in User 3
			System.out.println("Ci sono " + mydata.getSize("User 1", "Pass 1") + " elementi in User 1");
			System.out.println("Ci sono " + mydata.getSize("User 2", "Pass 2") + " elementi in User 2");
			System.out.println("Ci sono " + mydata.getSize("User 3", "Pass 3") + " elementi in User 3");}
		catch (FailedAuthenticationException e13) { System.err.println("Non sei registrato!");}
		
		
		//User 1 condivide il suo dato 'd3' con User 2; User 2 non ce l'aveva prima della share quindi viene aggiunto alla collezione di User 2	
		try { mydata.share("User 1", "Pass 1", "User 2","d3");
		}
		catch (NullPointerException e15) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e16) { System.err.println("Non sei registrato!");}
		catch (DuplicateDataException e17) { System.err.println("Il dato è già presente!");}
		catch (NotFoundDataException e18) { System.err.println("Dato non trovato!");}
			
		//ci sono 3 elementi in User 1, 1 in User 2 e 3 elementi in User 3	
		try { System.out.println("Ci sono " + mydata.getSize("User 1", "Pass 1") + " elementi in User 1");
			System.out.println("Ci sono " + mydata.getSize("User 2", "Pass 2") + " elementi in User 2");
			System.out.println("Ci sono " + mydata.getSize("User 3", "Pass 3") + " elementi in User 3");}
		catch (FailedAuthenticationException e13) { System.err.println("Non sei registrato!");}
		
		
		//restituisce la copia del d1
		try { 
			System.out.println("La copia è " + mydata.get("User 1" ,"Pass 1", "d1"));
		}
		catch (NullPointerException e21) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e22) { System.err.println("Non sei registrato!");}
		
		
		//restituisce la copia del d4
		try { 
			System.out.println("La copia è " + mydata.get("User 1" ,"Pass 1", "d4"));
		}
		catch (NullPointerException e21) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e22) { System.err.println("Non sei registrato!");}
		
		
		// copio 4 volte il dato d2 di user 1, aumentando le occorrenze di d2
		try { 
			mydata.copy("User 1" ,"Pass 1", "d2");
			mydata.copy("User 1" ,"Pass 1", "d2");
			mydata.copy("User 1" ,"Pass 1", "d2");
			mydata.copy("User 1" ,"Pass 1", "d2");}
		catch (NullPointerException e23) { System.err.println("Hai inserito un parametro nullo!");}
		catch (NotFoundDataException e24) { System.err.println("Dato non trovato!");}
		catch (FailedAuthenticationException e25) { System.err.println("Non sei registrato!");}
		
		//User 1 condivide il suo dato 'd3' con User 3; User 3 non ce l'aveva prima della share quindi viene aggiunto a User 3	
		try { mydata.share("User 1", "Pass 1", "User 3","d3");
		}
		catch (NullPointerException e15) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e16) { System.err.println("Non sei registrato!");}
		catch (DuplicateDataException e17) { System.err.println("Il dato è già presente!");}
		catch (NotFoundDataException e18) { System.err.println("Dato non trovato!");}
	
		try { //rimuovo il dato d1 che è presente in User 3, decrementando le occorrenze
			System.out.println("Rimuovo il dato 'd1' che è presente in User 3 e ottengo: " + mydata.remove("User 3", "Pass 3", "d1") );}
		catch (FailedAuthenticationException e9) { System.err.println("Non sei registrato!");}
		catch (NullPointerException e23) { System.err.println("Hai inserito un parametro nullo!");}
		
		try { //rimuovo il dato d2 che è presente in User 1, togliendolo dalla collezione
			System.out.println("Rimuovo il dato 'd2' che è presente in User 1 e ottengo: " + mydata.remove("User 1", "Pass 1", "d2") );}
		catch (FailedAuthenticationException e9) { System.err.println("Non sei registrato!");}
		catch (NullPointerException e23) { System.err.println("Hai inserito un parametro nullo!");}
	
		
		//itera sulla collezione di dati di User 1
		try { 
			Iteratore<String> it = mydata.getIterator("User 1","Pass 1");
			
			while(it.hasNext()) {
				Data<String> obj = it.next();
				
				System.out.println("Iterazione dei dati u1:" + obj.getDato());
				System.out.println("Occorrenze:" + obj.getOcc());
			}
		}
		catch (FailedAuthenticationException e24) { System.err.println("Non sei registrato!");}
		
		
		//itera sulla collezione di dati di User 2
		try {	Iteratore<String> it = mydata.getIterator("User 2","Pass 2");
			
			while(it.hasNext()) {
				Data<String> obj = it.next();
				
				System.out.println("Iterazione dei dati u2:" + obj.getDato());
				System.out.println("Occorrenze:" + obj.getOcc());
			}
		}
		catch (FailedAuthenticationException e24) { System.err.println("Non sei registrato!");}
		
		//itera sulla collezione di dati di User 3
		try {
			Iteratore<String> it = mydata.getIterator("User 3","Pass 3");
			
			while(it.hasNext()) {
				Data<String> obj = it.next();
				
				System.out.println("Iterazione dei dati u3:" + obj.getDato());
				System.out.println("Occerrenze:" + obj.getOcc());
			}
		}
		catch (FailedAuthenticationException e24) { System.err.println("Non sei registrato!");}
		
		
	
	}	
}
