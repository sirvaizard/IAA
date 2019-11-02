import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SortTimeCalculator {
	public static void main(String[] args) throws IOException {
		File inputFile, outputFile;
		FileWriter fw;
		BufferedWriter bw;
		double start;
		int readTime, sortTime;
		String[] prefix = {"1K", "10K", "100K", "1M"};

		outputFile = new File("output.txt");
		if(!outputFile.exists()) outputFile.createNewFile();

		fw = new FileWriter(outputFile);
		bw = new BufferedWriter(fw);

		for(int y = 0; y < 4; y++) {
			for(int x = 1; x <= 250; x++) {
				String fileName = prefix[y] + "_Array_" + x + ".txt";
				String fullPath = System.getProperty("user.dir") + "/arrays/" + fileName;
				start = System.nanoTime();
				inputFile = new File(fullPath);
				readTime = (int)(System.nanoTime() - start);
				Scanner sc = new Scanner(inputFile);
				
				int[] numbers = new int[sc.nextInt()];
				for(int k = 0; k < numbers.length; k++) {
					numbers[k] = sc.nextInt();
				}

				start = System.nanoTime();
				quickSort(numbers, 0, numbers.length - 1);
				sortTime = (int)(System.nanoTime() - start);
				saveLog(bw, readTime, sortTime, numbers.length, fileName);
				showProgress((y * 250) + x);
				sc.close();
			}
		}

		bw.close();
		fw.close();
	}

	public static void quickSort(int arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);
	
			quickSort(arr, begin, partitionIndex-1);
			quickSort(arr, partitionIndex+1, end);
		}
	}

	public static int partition(int arr[], int begin, int end) {
		int pivot = arr[end];
		int i = (begin-1);
	
		for (int j = begin; j < end; j++) {
			if (arr[j] <= pivot) {
				i++;
	
				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}
	
		int swapTemp = arr[i+1];
		arr[i+1] = arr[end];
		arr[end] = swapTemp;
	
		return i+1;
	}
	
	public static void saveLog(BufferedWriter bw, int readTime, int sortTime, int arraySize, String fileName) {
		try {
			bw.write(fileName + " ");
			bw.write(arraySize + " ");
			bw.write(readTime + " ");
			bw.write(sortTime + " ");
			bw.write("i5_3570_8GBDDR3_SSD500GB ");
			bw.write("quickSort ");
			bw.write("openjdk_11.0.5-ea ");
			bw.write("Ubuntu_19.10 ");
			bw.write("64 ");
			bw.write("N/A ");
			bw.write("11270802");
			bw.write("\n");
		} catch(Exception e) {
			System.out.println("Erro ao escrever no arquivo: \n" + e);
		}
	}

	public static void showProgress(int atual) {
		double percentage = (atual / 1000.0) * 100;
		System.out.printf("%.2f%% ( %d/1000 )\r", percentage, atual);
	}
}