import java.util.*;

// Merge Sort Implementation
public class MergeSort {
    public static void mergeSort(int[] array) {
        // Base case: return if the array has 1 or fewer elements
        if (array.length <= 1) return;

        // Split the array into two halves
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        // Recursively sort both halves
        mergeSort(left);
        mergeSort(right);

        // Merge the two sorted halves
        merge(array, left, right);
    }

    public static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Compare elements from both parts and insert the smaller one into the array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy remaining elements from the left part
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copy remaining elements from the right part
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}