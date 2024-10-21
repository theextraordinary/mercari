import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong();
        long parent=1;
        long children=2;
        while(children<n){
            parent++;
            children+=parent;
        }
        System.out.println(parent);

    }
}