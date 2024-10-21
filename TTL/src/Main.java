import java.util.*;

class Solution {
    public List<Integer> solve(List<int[]> arr, List<Integer> queries, int n, int q) {
        List<int[]> time = new ArrayList<>();
        for (int[] a : arr) {
            time.add(new int[]{a[0], 1});
            time.add(new int[]{a[0] + a[1], -1});
        }

        // Sort the time array based on the first value of each pair (event time)
        time.sort(Comparator.comparingInt(o -> o[0]));

        int m = time.size();
        int[] pref = new int[m];
        pref[0] = time.get(0)[1];

        // Create prefix sum array based on event times
        for (int i = 1; i < m; ++i) {
            pref[i] = pref[i - 1] + time.get(i)[1];
        }

        List<Integer> ans = new ArrayList<>();

        // Process each query
        for (int qVal : queries) {
            int[] pr = {qVal, -1};  // Event pair to search

            // Find the upper bound (first element greater than the query)
            int index = upperBound(time, pr);
            if (index == 0) {
                ans.add(0);  // If no event has started before the query time
            } else {
                ans.add(pref[index - 1]);  // Get the active count from the prefix sum
            }
        }
        return ans;
    }

    // Helper function for upper bound (binary search)
    private int upperBound(List<int[]> time, int[] pr) {
        int lo = 0, hi = time.size();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (time.get(mid)[0] <= pr[0]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<int[]> arr = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            int start = sc.nextInt();
            int duration = sc.nextInt();
            arr.add(new int[]{start, duration});
        }

        int q = sc.nextInt();
        List<Integer> queries = new ArrayList<>();

        for (int i = 0; i < q; ++i) {
            queries.add(sc.nextInt());
        }

        Solution s = new Solution();
        List<Integer> ans = s.solve(arr, queries, n, q);

        for (int a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();

        sc.close();
    }
}
