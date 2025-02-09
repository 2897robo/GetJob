public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // Loop through the entire array
        for (int i = 0; i < n - 1; i++) {
            // Assume the minimum is the first element
            int minIndex = i;
            // Find the index of the minimum element
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
