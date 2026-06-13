

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class Espino_Mod2_2_csd420 {
	public static void main(String[] args) {
		String fileName = "Espino_datafile.dat";
		File file = new File(fileName);


		if (!file.exists()) {
			System.out.println("No file found. Please run the WriteRandomData program first.");
			return;
		}

		try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
			int recordNum = 1;


			while (true) {
				System.out.println("--- Record Set " + recordNum + "---");


				int intLength = dis.readInt();
				System.out.print("Integers: ");
				for (int i = 0; i < intLength; i++) {
					System.out.print(dis.readInt() + " ");
				}

				int dblLength = dis.readInt();
				System.out.print("\nDoubles: ");
				for (int i = 0; i < dblLength; i++) {
					System.out.print(dis.readDouble() + " ");
				}
				System.out.println("\n");
				recordNum++;
				
			    } 
		    }
			catch (EOFException e) {
				System.out.println("End of file reached.");
			} catch (IOException e) {
				System.err.println("Error reading file: " + e.getMessage());
			}
		} 
	}