package dp;

public class LT509_Fib {
    public static int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        return fib(n - 1) + fib(n - 2);
    }

    public static int fib_extra(int n){
        int a = 0;
        int b = 1;

        for(int i = 2; i <= n; i++){
            int temp = a;
            a = b;
            b = temp + a;
        }

        return b;
    }

    public static int fib_bottom_up(int n){
        int[] fibs = new int[n + 1];

        fibs[0] = 0;
        fibs[1] = 1;

        for(int i = 2; i <= n; i++){
            fibs[i] = fibs[i - 1] + fibs[i - 2];
        }
        return fibs[n];
    }

    // (O(n) because each num calculated one time)
    public static int fib_top_down(int n){
        int[] fibs = new int[n + 1];


        for(int i = 0; i <= n; i++){
            fibs[i] = Integer.MAX_VALUE;
        }
        fibs[0] = 0;
        fibs[1] = 1;
        return inner_fib(fibs, n);
    }

    private static int inner_fib(int[] fibs, int n){
        if(fibs[n] != Integer.MAX_VALUE){
            return fibs[n];
        }

        fibs[n] = inner_fib(fibs, n-1) + inner_fib(fibs, n-2);
        return fibs[n];
    }



    public static void main(String[] args) {
        System.out.println(fib(6));
        System.out.println(fib_top_down( 6));
        System.out.println(fib_bottom_up(6));
        System.out.println(fib_extra(6));
    }
}
