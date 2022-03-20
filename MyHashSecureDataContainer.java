import java.util.Hashtable;

public class MyHashSecureDataContainer <E extends Comparable <E>> implements SecureDataContainer<E>{
		
	/* A.F: {< K,V >}, dove: K={ < Id_i > } e V = { < h.get(Id) > }, t.c 0 <= i < dim
	 *  
	 * I.R: 0 <= dim = h.size() && U != null
	 * 		&& (for all i, t.c i appartiene a h.keySet() ==> i != null)
     *      && (for all i, t.c i appartiene a h.keySet() ==> h.get(i) != null)     */
	
		
	private Hashtable <String,UserInfo<E>> h;
	int dim;


	public  MyHashSecureDataContainer () {
		// crea una tabella hash con key di tipo String e value di tipo UserInfo e inizializza dim a 0
		h=new Hashtable<String,UserInfo<E>>();
		dim=0;
	}

	
		//verifica l'identità dell'utente, controllando che Owner e passw corrispondano
	private UserInfo<E> VerifiedIdentity(String Owner,String passw) throws NullPointerException{
		
		if (Owner == null || passw == null) throw new NullPointerException();
		if(h.get(Owner)!=null && h.get(Owner).getPassw().equals(passw) )
				return h.get(Owner);	
		
		else return null;
	}
	
	//verifica che Owner appartenga alla collezione di utenti
	private UserInfo<E>  VerifiedIdentity(String Owner) throws NullPointerException{

			
		if (Owner == null) throw new NullPointerException();
		if(h.containsKey(Owner))
				return h.get(Owner);	
		
		else return null;
	}
	

	public int sizeU() {
		
		return dim;
	}
	
	
	public void createUser(String Id, String passw) throws NullPointerException,NameAlreadyExistsException{
		
	
		if(Id == null || passw == null)throw new NullPointerException();
		
		if(!h.containsKey(Id)) {
			h.put(Id, new UserInfo<E>(passw));
			dim++;
		}
		else throw new NameAlreadyExistsException();
	}
	

	
	public int getSize(String Owner, String passw) throws NullPointerException,FailedAuthenticationException{
		
			if(Owner == null || passw == null) throw new NullPointerException();
		if(VerifiedIdentity(Owner,passw)!=null) {
			return h.get(Owner).contaDati;
		}
		throw new  FailedAuthenticationException();
	}
	
	

	public boolean put(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException{
		
		
		if (Owner == null || passw == null || data == null) throw new NullPointerException();
		
		if(VerifiedIdentity(Owner,passw) != null) {
			if((h.get(Owner).CercaDato(data)) == -1) {	
				h.get(Owner).addDato(data);
				return true;
			}	
		else return false;
		}
		else throw new FailedAuthenticationException();
	
	}

	

	public E get(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException{

	
		if (Owner == null || passw == null || data == null) throw new NullPointerException();
		if(VerifiedIdentity(Owner,passw) != null) {
			int i=h.get(Owner).CercaDato(data);
			if(i != -1) {
				return h.get(Owner).getVectDati().get(i).getDato();
			}
			return null;
		}
		else throw new FailedAuthenticationException();
	}	

	
	
	public E remove(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException{
		
		
		if (Owner == null || passw == null || data == null) throw new NullPointerException(); 
		
		if(VerifiedIdentity(Owner,passw)!=null) {
			return h.get(Owner).removeDato(data);
		}
		else throw new FailedAuthenticationException();
	
	}



	public void copy(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException,NotFoundDataException{

	
		if (Owner == null || passw == null || data == null) throw new NullPointerException(); 
	
		if(VerifiedIdentity(Owner,passw) != null) {
			if(h.get(Owner).copy(data))
				return;
			else  throw new NotFoundDataException();
		}
		else throw new FailedAuthenticationException();	
	}


	public void share(String Owner, String passw, String Other, E data) throws NullPointerException,NotFoundDataException,DuplicateDataException,FailedAuthenticationException{
		
		
		if (Owner == null || passw == null || data == null || Other == null) throw new NullPointerException();
	
		if(VerifiedIdentity(Owner,passw) != null && VerifiedIdentity(Other) != null) {
			int i=h.get(Owner).CercaDato(data);
			int j=h.get(Other).CercaDato(data);
		
			if(i != -1 && j == -1) {
				h.get(Other).addDato(data);	
			}
			else if(i==-1) throw new NotFoundDataException();
			else if(j!=-1) throw new DuplicateDataException();
		
		}
		else new FailedAuthenticationException();
	}

	

	public Iteratore<E> getIterator(String Owner, String passw) throws NullPointerException,FailedAuthenticationException{
		
		if (Owner == null || passw == null ) throw new NullPointerException();
		if(VerifiedIdentity(Owner,passw) != null) {
			return new Iteratore<E>(h.get(Owner).getVectDati());
		}
		
		throw new FailedAuthenticationException();
	}
}			
