import java.util.Arrays;

public class MyClass {
    public static void main(String args[]) {
        int numbers[] = {512, 697, 14, 65, 236, 7458, 145, 37, 0, 985, 3146, 6578, 1426, 54, 1, 9999};
        
        numbers = radixSort(numbers, 4);
        
        System.out.println(Arrays.toString(numbers));
    }
    
    public static int[] radixSort(int[] A, int d) {
        int C[] = new int[10];
        int B[] = new int[A.length];
        
        for(int y = 1; y <= d; y++) {
            for(int x = 0; x < C.length; x++) C[x] = 0;
            for(int x = 0; x < A.length; x++) C[(int)(A[x] / Math.pow(10, y-1)) % 10]++;
            for(int x = 1; x < C.length; x++) C[x] += C[x-1];
            
            for(int x = A.length - 1; x >= 0; x--) {
                int indice = (int)(A[x] / Math.pow(10, y-1)) % 10;
                B[C[indice] - 1] = A[x];
                C[indice]--;
            }
            A = B.clone();
        }
        
        return B;
    }
    
    public static int[] countingSort(int[] A, int k) {
        int C[] = new int[k];
        int B[] = new int[A.length];
        
        for(int x = 0; x < k; x++) C[x] = 0;
        for(int x = 0; x < A.length; x++) C[A[x]]++;
        for(int x = 1; x < k; x++) C[x] += C[x-1];
        
        for(int x = A.length - 1; x >= 0; x--) {
            B[C[A[x]] - 1] = A[x];
            C[A[x]]--;
        }
        
        return B;
    }
}
