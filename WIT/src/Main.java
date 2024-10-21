import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int getMinOperations(List<Integer> arr) {
        int n = arr.size();
        int ans = 0;
        for (int j = 0; j < 32; ++j) {
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if ((arr.get(i) >> j & 1) == 1) {
                    cnt += 1;
                }
            }
            ans += Math.min(n - cnt, cnt);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        List<Integer> arr=new ArrayList<>();
        for(int i=0;i<n;i++)
            arr.add(sc.nextInt());
         // example input
        System.out.println("Minimum operations: " + getMinOperations(arr));
    }
}
