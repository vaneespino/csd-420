


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;


public class Espino_mod3_csd420 {

	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		LinkedHashSet<E> set = new LinkedHashSet<>(list);
		return new ArrayList<>(set);
	}


	public static void main(String [] args) {
		ArrayList<Integer> originalList = new ArrayList<>();
		Random random = new Random();


		for (int i = 0; i < 50; i++) {
			originalList.add(random.nextInt(20) + 1);
		}


		ArrayList<Integer> uniqueList = removeDuplicates(originalList);

		System.out.println("Original list size: " + originalList.size());
		System.out.println("Original list: " + originalList);


		System.out.println("\nUnique list size: " + uniqueList.size());
		System.out.println("Unique list: " + uniqueList);
	}
}
