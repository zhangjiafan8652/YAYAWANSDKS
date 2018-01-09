package nihaoya;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;



public class MyLinklist<E> extends LinkedList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		
		try {
			Method method = e.getClass().getMethod("compareTo", e.getClass());
			for (int i = 0; i < this.size(); i++) {
				int invoke = (Integer) method.invoke(e,this.get(i) );
				//System.out.println(invoke);
				if (invoke==0) {
					
					return false;
				}
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		return super.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		Iterator<? extends E> iterator = c.iterator();
		while (iterator.hasNext()) {
			
			E next = iterator.next();
			add(next);
			
		}
		return true;
	}
	
}
