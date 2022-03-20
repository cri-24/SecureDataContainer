import java.util.Vector;

/* OVERVIEW: tipo modificabile che rappresenta un utente, caratterizzato da password e collezione di dati
 * TYPICAL ELEMENT: < passw, D >, dove D = { <d1,occ1>,...,<dn,occn> }
 * AF: < getPassw(), getVectDati() >
 * IR: Passw != null && D != null && contaDati >= 0
 *     && for all i . 1 <= i <= n t.c d_i appartiene a {d1,...,dn} ==> d_i != null
 *     && for all i . 1 <= i <= n  ==> occ_i > 0
 *     && for all i, j . 1 <= i < j <= n t.c  d_i,d_j appartengono a {d1,...,dn} ==> (d_i != d_j)
 */

public class UserInfo<E extends Comparable <E>> {
	private String passw;
	private Vector<Data<E>> D;
	int contaDati=0;
	
	public UserInfo(String passw, Vector<Data<E>> vett)  throws NullPointerException {
		if(vett==null) throw new NullPointerException();
		if(passw==null) throw new NullPointerException();
		this.passw=passw;
		this.D=vett;	
	}
	
	public UserInfo(String passw) throws NullPointerException{
		this.passw=passw;
		this.D=new Vector<>();
		
	}
	
	public String getPassw() {
		return passw;
	}
	
	public Vector<Data<E>> getVectDati() {
		return D;
	}
	
	public int getContaDati(){
		return contaDati;
	}
	
	// aggiunge il dato e incrementa il contatore dei dati
	public void addDato(E dato) throws NullPointerException{ 
		
		if(dato==null) throw new NullPointerException();
		D.add(new Data<E>(dato));
		contaDati++;
			
	}
	
	//cerca il dato nella collezione e se lo trova restituisce l'indice, altrimenti -1	
	public int CercaDato(E dato) throws NullPointerException{ 
		
		if(dato==null) throw new NullPointerException();
		for(int i=0; i<D.size(); i++) {
			Data<E> data=D.get(i);
			if(data.getDato().compareTo(dato)==0)
				return i;		
		}
		return -1;
	}
	
	
	//se occ=1 rimuove 'dato' dalla collezione e lo restituisce, altrimenti se occ>1 rimuove 'dato' decrementando le occorrenze e lo restituisce, altrimenti se 'dato' non c'è restituisce null
	public E removeDato (E dato) throws NullPointerException { 
		if(dato==null) throw new NullPointerException();
		int i=CercaDato(dato);
		
			if(i != -1) {
				Data<E> data=D.get(i);
				if(data.getOcc()>1) {
					data.decrOcc();
					contaDati--;
					return dato;
				}
				else {
					contaDati--;
					return D.remove(i).getDato();
				}
			}
			else return null;
	}
	
	//incrementa le occorrenze di 'dato' e incrementa il contaDati, se è presente e ritorna true, altrimenti se 'dato non è presente ritorna false
	public boolean copy (E dato)  throws NullPointerException {
		if(dato==null) throw new NullPointerException();
		for(int i=0; i<D.size(); i++) {
			Data<E> data=D.get(i);
			if(data.getDato().compareTo(dato)==0) {
				data.incrOcc();
				contaDati++;
				return true;
			}
		}
		return false;
	}

	
}
