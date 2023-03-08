import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Practice3Answer {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.print("Please input the function No:\n" +
                    "1 - Get even numbers\n" +
                    "2 - Get odd numbers\n" +
                    "3 - Get prime numbers\n" +
                    "4 - Get prime numbers that are bigger than 5\n" +
                    "0 - Quit\n" );

            int code=sc.nextInt();
            if(code==0){
                break;
            }
            System.out.print("Input size of the list:\n");
            int N=sc.nextInt();
            int [] arr=new int[N];
            List<Integer> list=new ArrayList<>();
            System.out.print("Input elements of the list:\n");
            for (int i = 0; i < N; i++) {
                arr[i]=sc.nextInt();
                list.add(arr[i]);
            }
            System.out.print("Filter results:\n");
            switch (code){
                case 1:
                    list.removeIf(o->o%2!=0);
                    //list.toArray();
                    System.out.println(list);
                    break;
                case 2:
                    list.removeIf(o->o%2==0);
                    //list.toArray();
                    System.out.println(list);
                    break;
                case 3:
                    list.removeIf(o->(o%2==0&&o!=2)||(o%3==0&&o!=3)||(o%5==0&&o!=5));
                    //list.toArray();
                    System.out.println(list);
                    break;
                case 4:
                    list.removeIf(o->((o%2==0&&o!=2)||(o%3==0&&o!=3)||(o%5==0&&o!=5))||o<=5);
                    //list.toArray();
                    System.out.println(list);
                    break;


            }


        }
        sc.close();
        //System.exit(0);








    }
}
