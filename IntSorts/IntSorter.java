

public class IntSorter {

    public static void bubbleSort(int[] input) {
        int unprocessedLength = input.length;
        while (unprocessedLength > 1) {
            for (int i = 0; i < unprocessedLength - 1; i++) {
                int left = input[i];
                int right = input[i + 1];
                if (left > right) {
                    input[i + 1] = left;
                    input[i] = right;
                }
            }
            unprocessedLength--;
        }
    }

    public static void insertionSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int newIndex = findIndex(input, input[i], i);
        }
    }

    public static int findIndex(int[] input, int val, int length) {
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (input[i] >= val) {
                index = i + 1;
                break;
            }
            // index = (input[i] > val) ? i : index;
        }
        return index;
    }

    public static selectionSort(int[] input) {

    }

    public static quickSort(int[] input) {

    }

    public static mergeSort(int[] input) {

    }

    public static bucketSort(int[] input) {

    }

    public static radixSort(int[] input) {

    }

    public static heapSort(int[] input) {

    }
}
