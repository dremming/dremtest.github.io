public
class Primes {

public
    static void main(String[] args)
    {
        for (int i = 1; i <= 100; i++) {
            int b = 1;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    b = 0;
                    break;
                }
            }
            if (b == 1)
                System.out.print(" " + i);
        }
    }
}