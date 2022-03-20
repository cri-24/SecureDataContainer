import java.util.Vector;



public class MyVectSecureDataContainer <E extends Comparable <E>> implements SecureDataContainer<E>{
	
	/* AF: { < <U.get(i).getId(), U.get(i).getPassw()>, u.get(i).getVettDati() > | 0 <= i < dim }
	 * IR:  0 <= dim && dim = U.size() && U != null
     *      && for all i, 0 <= i < dim ==> Ui != null
     *      && (for all i, 0 <= i < dim ==> U.get(i) != null 
     *      && (for all i, j t.c 0 <= i < j < dim ==> U.get(i).getId()!= U.get(j).getId())
     *      && (for all i, 0 <= i < dim) ==> U.get(i).getVettDati != null 
     */
	
	private Vector<Utente<E>> U;  //vettore di utenti
	private int dim;   //numero di utenti
		
		public MyVectSecureDataContainer () {
			
			//EFFECTS: crea il vettore di utenti e inizializza dim a 0
			
			this.U=new Vector<Utente<E>>();
			dim=0;
		}
		
		public MyVectSecureDataContainer (Vector<Utente<E>> U) {
			
			/* REQUIRES: U != null 
			 * THROWS: if(U != null) throws NullPointerException (unchecked exception);
			 * EFFECTS: iniazializza this.U con il vettore passato come parametro e inizializza dim a U.size()
			 */
			
			this.U=U;
			dim=U.size();
		}
		
	
		private Utente<E> VerifiedIdentity(String Owner,String passw) throws NullPointerException{
			
			/* REQUIRES: Owner != null && passw != null
			 * THROWS: if(Owner==null || passw == null) throws NullPointerException (unchecked exception);
			 * EFFECTS: ritorna Owner, se <Owner,passw> appartiene a {<U.get(i).getId(), U.get(i).getPassw()>}, altrimenti ritorna null
			 */
			
			if (Owner == null || passw == null) throw new NullPointerException();
			for(int i=0; i<U.size(); i++) {
				Utente<E> u= U.get(i);
				if(u.getId().equals(Owner) && u.getPassw().equals(passw))
					return u;	
			}
			return null;
		}
		
		private Utente<E> VerifiedIdentity(String Owner) throws NullPointerException{
			
			/* REQUIRES: Owner != null 
			 * THROWS: if(Owner==null) throws NullPointerException) (unchecked exception)
			 * EFFECTS: ritorna Owner, se Owner appartiene {}, altrimenti ritorna null
			 */
			
			if (Owner == null) throw new NullPointerException();
			for(int i=0; i<U.size(); i++) {
				Utente<E> u= U.get(i);
				if(u.getId().equals(Owner))
					return u;	
			}
			return null;
		}
		
		
		
		public void createUser(String Id, String passw) throws NullPointerException, NameAlreadyExistsException{
		
			if (Id == null || passw == null) throw new NullPointerException();
			
			Utente<E> u= VerifiedIdentity(Id);
			if(u == null) {
				U.add(new Utente<E>(Id,passw));
				dim++;
			}
			else throw new NameAlreadyExistsException();			
		}
		
		
		
		public int getSize(String Owner, String passw) throws NullPointerException,FailedAuthenticationException{
		
			if (Owner == null || passw == null) throw new NullPointerException();
			Utente<E> u= VerifiedIdentity(Owner,passw);
			if(u != null)
				return u.contaDati;
			
			else throw new FailedAuthenticationException();
		}
			
		
		
	
		public boolean put(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException{
			
			if(Owner == null || passw == null || data==null)  throw new NullPointerException();
			
			Utente<E> u= VerifiedIdentity(Owner,passw);
			if(u != null) {
			int i = u.CercaDato(data);
			
				if(i == -1) {
					u.addDato(data);
					return true;
				}
				else return false ;
				
			}
			else throw new FailedAuthenticationException();
			
		}
		
		
		public E get(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException{
		
			if (Owner == null || passw == null || data == null) throw new NullPointerException();
			
			Utente<E> u = VerifiedIdentity(Owner, passw);
			if(u != null) {
				int i = u.CercaDato(data);
				if(i != -1) {
					return data;
				}
				else return null;
			}
			else throw new FailedAuthenticationException();
		}	
		
		public E remove(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException{
		
			if (Owner == null || passw == null || data == null) throw new NullPointerException(); 
			
			Utente<E> u = VerifiedIdentity(Owner, passw);
			if(u != null) {
					return u.removeDato(data);	
			}
			throw new FailedAuthenticationException();
			
		}
		
		public void copy(String Owner, String passw, E data) throws NullPointerException,FailedAuthenticationException,NotFoundDataException{
		
			if (Owner == null || passw == null || data == null) throw new NullPointerException(); 
			
			Utente<E> u= VerifiedIdentity(Owner, passw);
			if(u != null) {
				if(u.copy(data)) {	
					return;
				}
				else  throw new NotFoundDataException();
			}		
			else throw new FailedAuthenticationException();
		
			
		}
		
		public void share(String Owner, String passw, String Other, E data) throws NullPointerException,NotFoundDataException,DuplicateDataException,FailedAuthenticationException{
			
			if (Owner == null || passw == null || data == null || Other == null ) throw new NullPointerException();
		
			Utente<E> u1= VerifiedIdentity(Owner, passw);
			Utente<E> u2=VerifiedIdentity(Other);
			
			if(u1 != null && u2 != null) {			
					int i= u1.CercaDato(data);
					int j= u2.CercaDato(data);
		
					if(i != -1 && j == -1) {
						u2.addDato(data);
						return;
					}
					if (i == -1)throw new NotFoundDataException();
					if (j != -1)throw new DuplicateDataException();	
			}	
			else throw new FailedAuthenticationException();
		}
		
		public Iteratore<E> getIterator(String Owner, String passw) throws NullPointerException,FailedAuthenticationException{
		
			if (Owner == null || passw == null ) throw new NullPointerException();
			Utente<E> u = VerifiedIdentity(Owner, passw);
			if(u != null)
				return new Iteratore<E>(u.getVettDati());
			else throw new FailedAuthenticationException();
		}
		
		public int sizeU() {
			
			return dim;
			
		}
			
}
	

