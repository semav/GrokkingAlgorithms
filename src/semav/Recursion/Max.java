package semav.Recursion;

public class Max {
    public static void main(String[] args) {
        int[] array = {1,2,4,3,5,7,3,2,1};

        System.out.println(max(array));
    }

    private static int max(int[] array) {
        return max(array,0);
    }

    private static int max(int[] array, int from) {
        if (from == array.length - 1) {
            return array[from];
        }
        else {
            return Math.max(array[from], max(array, from + 1));
        }
    }
}
