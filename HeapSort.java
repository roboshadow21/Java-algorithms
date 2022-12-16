public class HeapSort {
    public void buildHeap(int[] arr, int size, int idx){
        int left = 2*idx+1;
        int right = 2*idx+2;
        int max = idx;
        if (left < size && arr[left] > arr[max]){
            max = left;
        }
        if (right < size && arr[right] > arr[max]){
            max = right;
        }
        if (max != idx){
            int spam = arr[idx];
            arr[idx] = arr[max];
            arr[max] = spam;
            buildHeap(arr, size, max);
        }
    }
    public void heapSort(int[] arr){
        if (arr.length == 0) return;
        int size = arr.length;
        for (int i = size / 2-1; i <= 0; i--){
            buildHeap(arr, size, i);
        }
        for (int i = size-1; i >= 0; i--){
            int spam = arr[0];
            arr[0] = arr[i];
            arr[i] = spam;
            buildHeap(arr, i, 0);
        }
    }
}
