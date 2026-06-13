

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Espino_Mod2_csd420 {
    public static void main(String[] args) {
        String fileName = "Espino_datafile.dat";
        Random rand = new Random();
        
        //5 random values
        int[] intArr = new int[5];
        double[] dblArr = new double[5];
        
        System.out.println("Generated Integers:");
        for (int i = 0; i < 5; i++) {
            intArr[i] = rand.nextInt(100);
            System.out.print(intArr[i] + " ");
        }
        
        System.out.println("\nGenerated Doubles:");
        for (int i = 0; i < 5; i++) {
            dblArr[i] = rand.nextDouble() * 100;
            System.out.print(dblArr[i] + " ");
        }
        
        
        //File handling; append if it exists, create if not
        File file = new File(fileName);
        boolean append = file.exists();
        
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, append))) {
           //Write integer array
           dos.writeInt(intArr.length);
           for (int val : intArr) {
               dos.writeInt(val);
           }
           
           //Write double array
           dos.writeInt(dblArr.length);
           for (double val : dblArr) {
               dos.writeDouble(val);
           }
           
           //Write double array
           dos.writeInt(dblArr.length);
           for (double val : dblArr) {
               dos.writeDouble(val);
           }
           
           System.out.println("\n\nData successfully written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}