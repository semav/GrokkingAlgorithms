package semav.DynamicProgramming;

import java.util.Arrays;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        String s1 = "fishds133331";
        String s2 = "hishda2333321asdshds1333";

        System.out.println(findLongestCommonSubstring(s1, s2));
    }

    private static String findLongestCommonSubstring(String s1, String s2) {
        int[][] grid = new int[s1.length()][s2.length()];
        int maxLength = 0;
        int maxRow = 0;

        for (int row = 0; row < s1.length(); row++) {
            char c1 = s1.charAt(row);
            for (int col = 0; col < s2.length(); col++) {
                char c2 = s2.charAt(col);

                if (c1 == c2) {
                    int length = prevLength(grid, row, col) + 1;

                    if (length > maxLength) {
                        maxLength = length;
                        maxRow = row;
                    }

                    grid[row][col] = length;
                }
                else
                    grid[row][col] = 0;
            }
        }

        for (int[] r : grid) {
            System.out.println(Arrays.toString(r));
        }

        return maxLength > 0 ? s1.substring(maxRow - maxLength + 1, maxRow + 1) : null;
    }

    private static int prevLength(int[][] grid, int row, int col) {
        if(row == 0 || col == 0)
            return 0;
        else
            return grid[row-1][col-1];
    }
}
