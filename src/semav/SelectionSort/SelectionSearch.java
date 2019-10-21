package semav.SelectionSort;

import java.util.Arrays;

public class SelectionSearch {
    public static void main(String[] args) {
        int[] array = {10,4,3,1,2,5,7,4,2,1,8,7,4,8,9};

        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++){
            int value = array[i];
            int index = i;

            for (int j = i + 1; j < array.length; j++){
                if (array[j] < value) {
                    value = array[j];
                    index = j;
                }
            }

            if (i != index){
                int tmp = array[i];
                array[i] = value;
                array[index] = tmp;
            }
        }
    }
}
