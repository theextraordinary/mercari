import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int[][][] dp = new int[1001][1001][26]; // Adjust size as needed based on the problem constraints

    private static char getCharWithDiff(char ch, int diff) {
        int code = ch - 'a';
        return (char) (((code + diff + 26) % 26) + 'a');
    }

    private static int dfs(String lotteryID, String winnerID, int i, int j, int remainingK, int m, int n) {
        if (i == m || j == n) return 0;
        if (remainingK < 0) return 0;
        if (dp[i][j][remainingK] != -1) return dp[i][j][remainingK];

        int result = Math.max(
                dfs(lotteryID, winnerID, i + 1, j, remainingK, m, n),
                dfs(lotteryID, winnerID, i, j + 1, remainingK, m, n)
        );

        if (lotteryID.charAt(i) == winnerID.charAt(j)) {
            result = Math.max(result, 1 + dfs(lotteryID, winnerID, i + 1, j + 1, remainingK, m, n));
        } else if (remainingK > 0) {
            for (int diff = 1; diff <= Math.min(25, remainingK); ++diff) {
                if (getCharWithDiff(lotteryID.charAt(i), diff) == winnerID.charAt(j) ||
                        getCharWithDiff(lotteryID.charAt(i), -diff) == winnerID.charAt(j)) {
                    result = Math.max(result, 1 + dfs(lotteryID, winnerID, i + 1, j + 1, remainingK - diff, m, n));
                    break;
                }
            }
        }

        dp[i][j][remainingK] = result;
        return result;
    }

    public static int maxLCSAfterOperations(String lotteryID, String winnerID, int k) {
        int m = lotteryID.length();
        int n = winnerID.length();
        for (int[][] d : dp) {
            for (int[] row : d) {
                Arrays.fill(row, -1);
            }
        }
        return dfs(lotteryID, winnerID, 0, 0, k, m, n);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String lotteryID = sc.next();
        String winnerID = sc.next();
        int k = sc.nextInt(); // example value of k

        System.out.println("Max LCS after operations: " + maxLCSAfterOperations(lotteryID, winnerID, k));
    }
}
