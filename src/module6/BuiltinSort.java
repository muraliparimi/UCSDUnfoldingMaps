package module6;

import java.util.*;

public class BuiltinSort {

	
	public static void main (String[] args) {
		Random random = new Random();
		
		List<Integer> mylist = new ArrayList();
		
		for (int i =0; i<=100; i++) {
			mylist.add(random.nextInt(2000));
		}
		
		System.out.println("Array before sort: "+ mylist.toString());
		Collections.sort(mylist);
		System.out.println("Array after sort: "+ mylist.toString());
	}
}
