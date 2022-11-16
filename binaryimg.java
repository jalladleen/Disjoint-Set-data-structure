import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class binaryimg {
	
	public static void connect(uandf imgSet, int img[][],int length)
	{
		int i = 0;
		int size = 0;
		
		while(i < img.length)
		{
			for (int j = 0; j < img.length; j++) 
			{
				if (img[i][j] == 1) 
				{
					size = (i * img.length + j) + 1; 
					imgSet.make_set(size);
					
					if (i > 0) 
					{
						if(img[i - 1][j] == 1)
							size = (i - 1) * img.length + j + 1;
							imgSet.union_sets(size, i * img.length + j + 1);
					}

					if (j > 0 ) 
					{
						if(img[i][j - 1] == 1)
							size = i * img.length + (j - 1) + 1 ;
							imgSet.union_sets(size, i * img.length + j + 1);
					}
				}	
			}
			i++;
		}
	}
		
	
	static void printP2(uandf imgSet, int sets[], int length)
	{
			int k;
			int i = 0;
			int size = 0;
 			
			while(i < length)
			{
				for (int j = 0; j < length; j++) {

					size = i * length + j + 1;
					k = imgSet.find_set(size) + 96;
					
					Character output;
		            output = k == 96 ? ' ' : (char) k; 
		            System.out.print(output);

					// If k is 96, then remove/print space
					if (k == 96) {}	
					else 
						//increment set
						sets[k - 97]++;
					
				}
				System.out.print("\n");
				i++;
			}
		}


	public static void print3(int sortedSet, int j)
	{
		
			String output = (char) (j + 97) + "\t  \t" + sortedSet;
			System.out.println(output);
	}
	
	
	
	public static void main(String[] args) throws IOException {
		int[][] img = new int[100][100];
		int[][] flag = new int[100][100];
		int[] sets = null;
		int[] chars = null;
		
		int x = 0;
		int y = 0;
		try {
			
			File imageFile = new File("./girl.img");
		//	Scanner scnr = new Scanner(imageFile);
			BufferedReader scnr = new BufferedReader(new InputStreamReader(System.in));
			String str;

			while(scnr.ready())
			{
				str = scnr.readLine();
				int strLength = str.length();
				for (int j = 0; j < strLength; j++) {
					if (str.charAt(j) == ('+')) {
						flag[x][j] = 1;
						img[x][j] = 1;	
					}
				}
				x++;
			}
			scnr.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
			System.out.println("Error: file is not found");
		} 
		
		// print Q1
		
		System.out.println("\n\nPrint Image in Binary:\n\n");
		x = 0;
		y = 0;
		while(x < img.length)
		{
			for (int j = 0; j < img.length; j++) {
				System.out.print(img[x][j]);
			}
			System.out.print("\n");
			x++;
		}
		
		int size = img.length * img.length + 1;
		uandf imgSet = new uandf(size);

		connect(imgSet,img, img.length);

		sets = new int[imgSet.final_sets()];
		chars = new int[sets.length];
		
		System.out.println("\n\nPrint the connected component image where each component is labelled with a unique character\n\n ");

		//print Q2
		printP2(imgSet, sets, img.length);

		int k;
		//print Q3
		System.out.println("a list sorted by component size, where each line of the list contains the size and the\r\n"
				+ "label of a component");
		
		
		int[] sortedSets = new int[sets.length];
		x = 0;
		
		while (x < sets.length)
		{
			sortedSets[x] = sets[x];
			x++;
		}
		
		x = 0;
		
		while(x < sortedSets.length)
		{
			chars[x] = sortedSets[x];
			x++;
		}
				
		Arrays.sort(sortedSets);
		
		int sortedSetL = sortedSets.length;
		int charsL = chars.length;
		x = 0;
		
		
		while (x < sortedSetL) {
			for (int j = 0; j < charsL; j++) 
			{
				if (sortedSets[x] == chars[j]) {
					print3(sortedSets[x], j);
					chars[j] = -1;
					break;
				}
			}
			
			x++;
		}

		//print Q4
		System.out.println("\n\nsame as 2 with the connected components whose sizes are less than Five deleted\n\n");
		int counter = 0;
		int sizeImg = img.length;
		while(counter < sizeImg) {
			for (int j = 0; j < sizeImg; j++) {
				k = imgSet.find_set(counter * sizeImg + j + 1) + 96;
				
				char output = k == 96 || sets[k - 97] < 5 ? ' ': (char)k;
				
				System.out.print(output);
			}
			System.out.println();
			
			counter++;
		}
		System.out.println();
	}



}