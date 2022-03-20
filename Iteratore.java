import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Vector;
	

public class Iteratore<E> implements Iterator<Data<E>> {

		private Vector<Data<E>> v1;
		private Vector<Data<E>> v2;
	    int index;

	   
		
		public Iteratore(Vector<Data<E>> vectDati) {
			
			v1= new Vector<Data<E>>();
			for (Data<E> el: vectDati)
				v1.add(el);
			this.v2=vectDati;
			index=0;
	    }
	        
	    public  boolean hasNext(){
	    	 if (!v2.equals(v1)) throw new ConcurrentModificationException();
	        return (index <v1.size());
	     }

	    public  Data<E> next(){

	 	return v1.elementAt(index++);
	     } 
	    
	    public  void remove(){
	    	throw new UnsupportedOperationException("remove");
	     } 

	 } 

