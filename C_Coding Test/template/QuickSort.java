import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return; // 원소가 하나 이하이면 정렬 종료

        int pivot = partition(arr, left, right); // 분할 수행
        quickSort(arr, left, pivot - 1);  // 왼쪽 부분 정렬
        quickSort(arr, pivot + 1, right); // 오른쪽 부분 정렬
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // 가장 오른쪽 요소를 pivot으로 선택
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) { // pivot보다 작으면 왼쪽으로 보냄
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right); // pivot을 최종 위치로 이동
        return i + 1; // pivot의 위치 반환
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 2, 7, 1, 10};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 7, 8, 10]
    }
}