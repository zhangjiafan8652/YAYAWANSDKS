package nihaoya;

import java.util.ArrayList;
import java.util.HashSet;

public class ceshi2 {
	
	public static void main(String[] args) {
		testbean testbean1 = new testbean();
		testbean1.setId(1);
		testbean testbean2 = new testbean();
		testbean2.setId(2);
		testbean testbean3 = new testbean();
		testbean3.setId(3);
		ArrayList<testbean> arrayList = new ArrayList<testbean>();
		arrayList.add(testbean3);
		arrayList.add(testbean2);
		arrayList.add(testbean1);
		
		MyLinklist<testbean> hashSet = new MyLinklist<testbean>();
		hashSet.addAll(arrayList);
		hashSet.addAll(arrayList);
		hashSet.add(testbean3);
		MyLinkedList<testbean> myLinkedList = new MyLinkedList<testbean>();
		myLinkedList.addAll(arrayList);
		myLinkedList.addAll(arrayList);
		for (int i = 0; i < hashSet.size(); i++) {
			//System.out.println(hashSet.);
			System.out.println(hashSet.get(i).getId());
		}
		/*for (int i = 0; i < myLinkedList.size(); i++) {
			System.out.println(myLinkedList.get(i).getId());
		}*/
	}

}
