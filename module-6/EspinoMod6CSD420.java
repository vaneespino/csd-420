


import java.util.Arrays;
import java.util.Comparator;

public class EspinoMod6CSD420 {
    
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean needNextPass = true;
        
        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (list[i].compareTo(list[i + 1]) > 0){
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
    } 
    
    
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean needNextPass = true;
        
        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (comparator.compare(list[i], list[i + 1]) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
    }
    
    public static <E> void printArray(E[] list) {
        for (E element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("--- Test 1: Comparable Bubble Sort (Natural Order) ---");
        
        Integer[] intList = {4, 7, 1, 3, 2, 7, 5, 8, 6};
        System.out.println("Original Integer Array: ");
        printArray(intList);
        
        bubbleSort(intList);
        System.out.print("Sorted Integer Array:  ");
        printArray(intList);
        
        String[] stringList = {"Orange", "Pear", "Mango", "Banana", "Apple"};
        System.out.print("\nOriginal String Array;  ");
        printArray(stringList);
        
         bubbleSort(stringList);
        System.out.print("Sorted String Array:   ");
        printArray(stringList);
        
        System.out.println();
        
        System.out.println("--- Test 2: Comparator Bubble Sort (Descending / Custom Order) ---");
        
        
        Integer[] intList2 = {4, 8, 2, 9, 3, 7, 6, 5, 1};
        System.out.print("Original Integer Array: ");
        printArray(intList2);
            
        bubbleSort(intList2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        
        System.out.print("Sorted Integer Array (Descending): ");
        printArray(intList2);
        
        String[] stringList2 = {"Grape", "Cherry", "Apple", "Banana", "Orange"};
        System.out.print("\nOriginal String Array:  ");
        printArray(stringList2);
        
        bubbleSort(stringList2, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
        
        
        System.out.print("Sorted String Array (By Length):  ");
        printArray(stringList2);
    }
}
