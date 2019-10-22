package semav.Recursion;

public class Sum {
    public static void main(String[] args) {
        int[] array = {1,2,4,3,5};

        System.out.println(sum(array));
    }

    private static int sum(int[] array) {
        return sum(array,0);
    }

    private static int sum(int[] array, int from) {
        if (from >= array.length) {
            return 0;
        }
        else{
            return array[from] + sum(array, from + 1);
        }
    }
}
