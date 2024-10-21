import java.util.*;

public class Main {

    public static long solve(List<Integer> nums) {
        // Step 1: Find all prime numbers up to 200
        int n=nums.size();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=nums.get(i);
        }
        long cumprod[]=new long[n];
        cumprod[0]=arr[0];
        Set<Long> set=new HashSet<>();
        set.add((long)arr[0]);
        for(int i=1;i<n;i++) {
            cumprod[i] = cumprod[i - 1] * arr[i];
            set.add(cumprod[i]);
        }
        System.out.println(set);
        int ans=0;
        for(long j=1;j<=200;j++){
//            System.out.println(j*j);
            if(set.contains(j*j)) ans++;
        }
        System.out.println(ans);
        for(int i=0;i<n;i++){
            for(long j=2;j<=200;j++){
                if(set.contains(cumprod[i]*j*j)){
                    System.out.println(cumprod[i]+" "+j);
                    ans++;
                }

            }
        }



        return ans;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        List<Integer> arr=new ArrayList<>();
        for(int i=0;i<n;i++){
            arr.add(sc.nextInt());
        }
          // Example input
        System.out.println(solve(arr));
    }
}
