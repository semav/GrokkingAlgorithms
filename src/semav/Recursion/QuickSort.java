package semav.Recursion;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {9,8,7,6,5,4,3,2,1,0,-1,-2,-3,-4};

        quickSort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] array, int from, int to) {
        if (to <= from){
            return;
        }

        int partitionIndex = partition(array, from, to);
        System.out.println(Arrays.toString(array));

        quickSort(array, from, partitionIndex - 1);
        quickSort(array, partitionIndex + 1, to);
    }

    private static int partition(int[] array, int from, int to) {
        int pivot = array[to];
        int partitionIndex = from;

        for (int i = from; i < to; i++) {
            if (array[i] <= pivot) {
                swap(array, partitionIndex, i);

                partitionIndex++;
            }
        }

        // Swap partition and pivot
        swap(array, partitionIndex, to);

        return  partitionIndex;
    }

    private static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
}
