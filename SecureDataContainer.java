



public interface SecureDataContainer<E extends Comparable <E>>{
	
	/* OVERVIEW: tipo modificabile che rappresenta una collezione finita di utenti associati al proprio insieme finito di dati
	 * ELEMENTO TIPICO: { <U0,D0>,...,<Un-1,Dn-1> }, dove: 
	 * Ui = < Id_i, Passw_i > 
	 * Di = { <di_1,occi_1>...,<di_m,occi_1> } = insieme di dati e relative occorrenze, associati all'utente i-esimo Ui
	*/
	
	
	public void createUser(String Id, String passw) throws NullPointerException,NameAlreadyExistsException;
		
		/* REQUIRES: passw != null && Id != null
		 * THROWS: if (passw == null || Id == null) solleva NullPointerException (unchecked exception),
		 *	  	   if (Id appartiene a {U0.Id,...,Un.Id}) solleva NameAlreadyExistsException (checked exception)
		 * MODIFIES: this
		 * EFFECTS: aggiunge un nuovo utente nella collezione
		 */
	
	
	public int getSize(String Owner, String passw) throws NullPointerException,FailedAuthenticationException;
		
		/* REQUIRES:  Owner != null && passw != null && Ui = <Owner,passw> appartiene a {U0,...,Un-1} 
		 * THROWS:  if (<Owner,passw> non appartiene a {U0,...,Un-1}) solleva FailedAuthenticationException (checked exception);
		 * 			if( Owner == null || passw == null ) solleva NullPointerException (unchecked exception);
		 * MODIFIES: /
		 * EFFECTS: Restituisce il numero dei dati associati all'utente Owner, ovvero la cardinalità di {di_1,..di_m}
		 */

	public boolean put(String Owner, String passw, E data) throws NullPointerException, FailedAuthenticationException;
		
		/* REQUIRES: Owner != null && passw != null && data != null && Ui = <Owner,passw> appartiene a {U0,...,Un-1} && data != null
		 * THROWS: if(Owner == null || passw == null || data == null) solleva NullPointerException (unchecked exception);
		 * 		if (<Owner,passw> non appartiene a {U0,...,Un-1}) solleva FailedAuthenticationException (checked exception);
		 * MODIFIES: this
		 * EFFECTS:ritorna true se 'data' non appartiene a {di_1,..,di_m}, quindi l'inserzione avviene con successo, altrimenti ritorna false 
		 */
	
	public E get(String Owner, String passw, E data) throws NullPointerException, FailedAuthenticationException;
		
		/* REQUIRES: Owner != null && passw != null && data != null && Ui = <Owner,passw> appartiene a {U0,...,Un-1} && data != null && Owner != null && passw != null && data != null 
		 * THROWS: if(Owner == null || passw == null || data == null) solleva NullPointerException (unchecked exception);
		 * if (<Owner,passw> non appartiene a {U0,...,Un-1}) solleva FailedAuthenticationException (checked exception);
		 * MODIFIES: /
		 * EFFECTS: restituisce una copia del valore di data, se data appartiene a { di_1,...,di_m }, altrimenti restituisce null
		 */
	
	
	public E remove(String Owner, String passw, E data) throws NullPointerException, FailedAuthenticationException;
		
		/* REQUIRES: data != null && Owner != null && passw != null && Ui = <Owner,passw> appartiene a {U0,...,Un-1} && data != null
		 * THROWS: if(Owner == null || passw == null ||data == null) solleva NullPointerException (unchecked exception)
		 * if (<Owner,passw> non appartiene a {U0,...,Un-1}) solleva FailedAuthenticationException (checked exception);
		 * MODIFIES: this
		 * EFFECTS: restituisce null se 'data' non appartiene a { <di_1,occi_1>,...,<di_m,occi_m> }, altrimenti lo rimuove e lo restituisce
		 */
	
	public void copy(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException,NotFoundDataException;
	
		/* REQUIRES: data != null && Owner != null && passw != null && Ui = <Owner,passw> appartiene a {U0,...,Un-1} && data != null
		 * THROWS: if(Owner == null || passw == null ||data == null) solleva NullPointerException (unchecked exception);
		 * if (Owner non esiste nella collezione || Owner non è associato a passw) solleva FailedAuthenticationException (checked exception);
		 * if ('data' non appartiene a { di_1,...,di_m }) solleva NotFoundDataException (checked exception);
		 * MODIFIES: this
		 * EFFECTS: copia il dato in { di_1,...,di_m }
		 */
	
	
	public void share(String Owner, String passw, String Other, E data) throws NullPointerException,NotFoundDataException,DuplicateDataException,FailedAuthenticationException;
	
		/* REQUIRES: data != null && Owner != null && passw != null && Other != null && Ui = <Owner,passw> appartiene a {U0,...,Un-1} e Uj.Id = Other appartiene a {U0.Id,...,Un-1.Id} && data != null && 'data' appartenente a { di_1,...,di_m } && 'data' non appartenente a { dj_1,...,dj_m }
		 * THROWS: if (Owner == null || passw == null ||data == null || Other == null) solleva NullPointerException (unchecked exception);
		 * if (<Owner,passw> appartiene a {U0,...,Un-1} || Other non appartiene {U0.Id,...,Un-1.Id}) solleva FailedAuthenticationException (checked exception);
		 * if ('data' non appartenente a { di_m,...,di_m }) solleva NotFoundDataException (checked exception);
		 * if ('data' appartenente a { dj_1,...,dj_k }) solleva DuplicateDataException (checked exception);
		 * MODIFIES: this
		 * EFFECTS: condivide il dato di Owner con Other
		 */
	
	public Iteratore<E> getIterator(String Owner, String passw) throws NullPointerException,FailedAuthenticationException;
	
		/* REQUIRES: Owner != null && passw != null && Ui = <Owner,passw> appartiene a {U0,...,Un-1}
		 * THROWS: if(Owner == null || passw == null) solleva NullPointerException (unchecked exception);
		 * if ( <Owner,passw> non appartiene a {U0,...,Un-1}) solleva FailedAuthenticationException (checked exception);
		 * if ('data' non appartiene a { di_1,...,di_m }) solleva NotFoundDataException (checked exception);
		 * MODIFIES: /
		 * EFFECTS: restituisce un iteratore (senza remove) che genera l'insieme dei dati (in ordine arbitrario) e relative occorrenze, se Owner ha almeno un dato, altrimenti non restituisce niente	
		 */
	
	public int sizeU();
	
		//EFFECTS: restituisce il numero di utenti 
		
}			


		
	