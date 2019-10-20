package semav.Introduction;

import java.util.stream.IntStream;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = IntStream.range(1213, 33234223).toArray();

        int index = binarySearch(array, 23450);
        System.out.println(index);

        index = binarySearch(array, 23453430);
        System.out.println(index);

        index = binarySearch(array, 2350);
        System.out.println(index);

        index = binarySearch(array, 21450);
        System.out.println(index);

        index = binarySearch(array, 4510);
        System.out.println(index);
    }

    private static int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int middle = (high + low) / 2;

            if (array[middle] == value){
                return middle;
            }
            else if (value < array[middle]){
                high = middle - 1;
            }
            else {
                low = middle + 1;
            }
        }

        return -1;
    }
}
