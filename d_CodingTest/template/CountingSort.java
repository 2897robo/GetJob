import java.util.*;
import java.io.*;

public class CountingSort {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: size of the array, K: maximum value in the array
        int n = Integer.parseInt(br.readLine());

        // Initialize the input array
        int[] a = new int[n];
        int max = Integer.MIN_VALUE;

        // Read values into array 'a' and find the maximum value
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
            if (a[i] > max) max = a[i]; // Update max if current value is greater
        }

        // Create counting array B with a size of max + 1
        int[] b = new int[max + 1];
        // Result array C to store sorted values
        int[] c = new int[n];

        // Call the countingSort function to perform sorting
        countingSort(a, b, c, n, max);

        // Output the sorted result
        for (int i : c) {
            bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * Counting Sort algorithm
     * @param A Input array
     * @param B Counting array
     * @param C Result array (sorted)
     * @param n Size of the input array
     * @param k Maximum value in the input array A
     */
    public static void countingSort(int[] A, int[] B, int[] C, int n, int k) {
        // Initialize the counting array B with zeros
        for (int i = 0; i <= k; i++) {
            B[i] = 0;
        }

        // Count the frequency of each element in array A and store in array B
        for (int i = 0; i < n; i++) {
            B[A[i]]++;
        }

        // Update array B to hold the cumulative count
        for (int i = 1; i <= k; i++) {
            B[i] += B[i - 1];
        }

        // Place elements into the result array C using the cumulative counts
        for (int i = n - 1; i >= 0; i--) {
            C[B[A[i]] - 1] = A[i];
            B[A[i]]--;  // Decrease the count of the current element in B
        }
    }
}
