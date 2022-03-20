import java.util.Vector;

public class Utente<E extends Comparable<E>> {
	
	/*  OVERVIEW:tipo modificabile che rappresenta un utente, caratterizzato da Id e Passw a cui è associata una collezione di dati
	 *  ELEMENTOTIPICO: < Id_1 ,Passw_1, {<d1_1,occ1_1>,...,<d1_n,occ1_n>} >,...,< Id_n, Passw_n, {<dn_1,occn_1...,dn_j,occn_j} > , dove:
	 *  {<di_1,occi_1>,...,<di_k,occi_k>} la collezione associata all'utente i-esimo, formata da coppie <d,occ> di dato e rispettivo numero di occorrenze 
	 *  && k = contaDati 
	 *  AF: < getId(), getPassw(), D >, dove:
	 *  IR: Id != null && Passw != null && D != null && contaDati >= 0 &&
	 *     for all i . 1 <= i <= n t.c d_i appartiene a {d1,...,dk} ==> d_i != null &&
	 *     for all i . 1 <= i <= n  ==> occ_i > 0 &&
     *     for all i, j. 1 <= i < j <= n ==> (d_i != d_j)
	 */ 
	
	private String Id;
	private String Passw;
	private Vector<Data<E>> D;
	int contaDati;
	
	// inizializza this.Id a Id, this.Passw a Passw, this.contaDati a 0 e crea il vettore di tipo Data<E> 
	public Utente(String Id, String Passw) throws NullPointerException {
	
		if(Id==null) throw new NullPointerException();
		if(Passw==null) throw new NullPointerException();
		this.Id=Id;
		this.Passw=Passw;
		D=new Vector<Data<E>>();
		contaDati=0;
	}
	
	//inizializza this.Id a Id, this.Passw a Passw, this.contaDati alla size di 'D', il quale gli passo
	public Utente(String Id, String Passw, Vector<Data<E>> D)  throws NullPointerException {
		
		 
		
		if(D==null) throw new NullPointerException();
		if(Passw==null) throw new NullPointerException();
		if(Id==null) throw new NullPointerException();
		this.Passw=Passw;
		this.D=D;	
		contaDati=D.size();
	}
	
	//ritorna il numero di dati 
	public int getContaDati(){
		return contaDati;
	}
	
	//ritorna l'Id dell'utente
	public String getId() {
		return Id;
	}
	
	//ritorna la Passw dell'utente
	public String getPassw() {
		return Passw;
	}
	
	//incrementa le occorrenze di 'dato' se è presente e incrementa il contatore dei dati, altrimenti non fa niente
	public void IncrOccDato(E dato) throws NullPointerException{ 
			
		if(dato==null) throw new NullPointerException();
		
		int i=CercaDato(dato);
		
		if(i != -1) {
			Data<E> data=D.get(i);
			data.incrOcc();
			contaDati++;
		}
	}
	
	// aggiunge il dato e incrementa il contatore dei dati
	public void addDato(E dato) throws NullPointerException{ 
		
		if(dato==null) throw new NullPointerException();
		
		D.add(new Data<E>(dato));
		contaDati++;
	}
	
	
	//cerca 'dato' e se lo trova restituisce l'indice, se non c'è restituisce -1
	public int  CercaDato(E dato) throws NullPointerException{ 
		
		if(dato == null) throw new NullPointerException();
		 
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
		
			if(i!=-1) {
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
	
	//modifica la password
	public void setPassw(String p) throws NullPointerException{
		if(p==null) throw new NullPointerException();
		this.Passw=p;
	}
	
	//incrementa le occorrenze di 'dato' e incrementa il contaDati, se è presente e ritorna true, altrimenti se 'dato non è presente ritorna false
	public boolean copy (E dato) throws NullPointerException {
		
		if(dato==null) throw new NullPointerException();
		int i=CercaDato(dato);
		
		if (i != -1) {
			Data<E> data=D.get(i);
			data.incrOcc();		
			contaDati++;
			return true;
		}
		else return false;
	}
	
	
	//ritorna il vettore di dati e occorrenze
	public Vector<Data<E>> getVettDati() {
		return D;
	}
	
}
