
public class Data<E> {
	
	/* 	OVERVIEW: tipo modificabile che rappresenta un dato
	 * 	TYPICAL ELEMENT: < dato ,occ > 
     *  AF : < getDato, getOcc >
     *  IR : dato != null && occ > 0 
	 */ 
	
	private E dato;
	private int occ; 
	
	//crea un dato in cui dato è di tipo E e occ è di tipo int e viene inizializzato a 1
	public Data(E dato) throws NullPointerException {
		
		if(dato==null) throw new NullPointerException();
		this.dato=dato;
		occ=1;
	}
	
	//restituisce il dato
	public E getDato() {
		
		return dato;
	}
	
	//restituisce il numero di occorrenze
	public int getOcc() {
		
		return occ;
	}
	
	//incrementa il numero di occorrenze
	public int incrOcc () {
		return occ++;
	}
	
	//decrementa il numero di occorrenze
	public void decrOcc() {
		 occ--;
	}
}
