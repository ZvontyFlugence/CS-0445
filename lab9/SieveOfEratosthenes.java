package cs445.lab9;

public class SieveOfEratosthenes {
    public static ListInterface<Integer> primesUpTo(int max) {
        ListInterface<Integer> primes = new ArrayList<>();
        for(int i = 1; i <= max; i++) {
            if(isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static boolean isPrime(int num) {
        for(int i = 2; i < num; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int end = 0;
        try {
            end = new Integer(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please use a integer parameter for maximum value");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please use a integer parameter for maximum value");
            return;
        }

        ListInterface<Integer> result = primesUpTo(end);
        if (result != null) {
            System.out.println("Primes:");
            for (int i = 0; i < result.getSize(); i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println(" ");
        } else {
            System.out.println("primesUpTo() returned null. Did you complete it?");
        }
    }
}
