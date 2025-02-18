import java.util.Arrays;

public class HeapSort {
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 1️⃣ 배열을 최대 힙(Max Heap)으로 변환
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 2️⃣ 힙에서 하나씩 요소를 꺼내 정렬
        for (int i = n - 1; i > 0; i--) {
            // 현재 최대값(루트)과 마지막 요소 교환
            swap(arr, 0, i);
            // 힙 크기 줄이고 다시 힙 성질 유지
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // 부모 노드
        int left = 2 * i + 1; // 왼쪽 자식
        int right = 2 * i + 2; // 오른쪽 자식

        // 왼쪽 자식이 더 크면 largest 변경
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        // 오른쪽 자식이 더 크면 largest 변경
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        // largest가 부모 노드가 아니라면 교환 후 재귀 호출
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 3, 4, 5, 10]
    }
}