
public class ExcHashTestMain {
	
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
		
		//restituisce 'nome già esistente' perchè inserisco User 3 che è già presente 
		try { 
			mydata.createUser("User 3" , "Pass 3");
			System.out.println("User 3 Pass3");	} 
		catch ( NameAlreadyExistsException e3) { System.err.println("Nome già esistente!");}
		catch ( NullPointerException e4) { System.err.println("Hai inserito un parametro nullo!");}
		
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
		
		//restituisce 'Hai inserito un parametro nullo', perchè inserisco NULL in User 3
		try { 
			System.out.println("Esito inserzione User 1:" + mydata.put("User 3", "Pass 3", null ));}
		catch (NullPointerException e9) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e10) { System.err.println("Non sei registrato!");}
		
		//restituisce 'non sei registrato', perchè inserisco la password sbagliata per User3
		try { 
			System.out.println("Esito inserzione User 1:" + mydata.put("User 3", "Pass 12345", "d1"));}
		catch (NullPointerException e11) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e12) { System.err.println("Non sei registrato!");}
	
		
		//restituisce 'il dato è già presente', perchè User 1 condivide il suo dato 'd3' con User 2, ma User 2 già ce l'aveva prima della share 
		try { 	mydata.share("User 1", "Pass 1", "User 3","d1");
		}
		catch (NullPointerException e19) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e20) { System.err.println("Non sei registrato!");}
		catch (DuplicateDataException e21) { System.err.println("Il dato è già presente!");}
		catch (NotFoundDataException e22) { System.err.println("Dato non trovato!");}
		
		//restituisce 'dato non trovato' perchè User 1 condivide il dato 'd6' con User 2; ma User 1 non ha il dato d6
		try { 	mydata.share("User 1", "Pass 1", "User 3","d6");
		}
		catch (NullPointerException e19) { System.err.println("Hai inserito un parametro nullo!");}
		catch (FailedAuthenticationException e20) { System.err.println("Non sei registrato!");}
		catch (DuplicateDataException e21) { System.err.println("Il dato è già presente!");}
		catch (NotFoundDataException e22) { System.err.println("Dato non trovato!");}
		
		
		// copio il dato 'd6' di User 1, ma User 1 non ha il dato 'd6'
		try { 
			mydata.copy("User 1" ,"Pass 1", "d6");}	
		catch (NullPointerException e23) { System.err.println("Hai inserito un parametro nullo!");}
		catch (NotFoundDataException e24) { System.err.println("Dato non trovato!");}
		catch (FailedAuthenticationException e25) { System.err.println("Non sei registrato!");}
		
		
		try { //rimuovo il dato 'd6' che non è presente in User 3
			System.out.println("Rimuovo il dato 'd6' che non è presente in User 3 e ottengo: " + mydata.remove("User 3", "Pass 3", "d6") );}
		catch (FailedAuthenticationException e9) { System.err.println("Non sei registrato!");}
		catch (NullPointerException e23) { System.err.println("Hai inserito un parametro nullo!");}
		
		
	
	}	
}
