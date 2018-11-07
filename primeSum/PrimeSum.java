import java.util.ArrayList;

/*
    PRIMESUM
    Given an even number ( greater than 2 ), return two prime numbers whose sum will be equal to given number.

    NOTE A solution will always exist. read Goldbach’s conjecture

    Example:


    Input : 4
    Output: 2 + 2 = 4

    If there are more than one solutions possible, return the lexicographically smaller solution.

    If [a, b] is one solution with a <= b,
    and [c,d] is another solution with c <= d, then

    [a, b] < [c, d] 

    If a < c OR a==c AND b < d.

    
    Solution

    For this one I did not implemented the most optimal solution.

    I first explain my workflow, then some corrections my solution does need
    
    I did follow the Sieve of Erastosthenes to compute prime numbers, i.e. for a given number, check wheter
    it can be divided by the numbers below.
    Actually, Erastosthenes gives you back the first N prime numbers excluding from a set all the numbers
    which can be divided.

    I have implemented the isPrime(n) function to check wheter "n" is prime or not. In this function I check if
    n is 1,2 or 3 and I exclude from the beginning number such that % 2 or 3 returns 0. Why? Just to avoid 1/2 + 1/3
    (more or less) of the possible inputs. The I iterate up to "n" and check if "n" % "i" is 0 or not.

    To find the primeSum though, I thought "let's build the array of prime number from 1 up to the input a,
    double iterate through this and find the first two primes that sum up to the input. In this way it's easy
    to maintain the specific of the lexicographic order".

    But doing in this way I comput all the prime numbers, also the ones I may not need; I also allocate
    as many elements as the prime number are; finally, I also have a double loop on this list of primes.

    But we can do better.

    First of all, the isPrime(n) function can iterate up to the sqrt(n) and not n. Why? Think of 100. The greatest divisor of
    100 is exactly 10. Any other number greater than 10 that divides 100 is already divisible by a number smaller than 10 (such as 20, 25 and 50).

    Second, instead to compute ALL the primes up to the input (called it A), we can start directly iterating with "j" from A-1 down to 1
    and check wheter "j" and "A-j" are primes: if they are, then (A-j, j) is the solution which satisfies also the lexicographic property
    (A-j is the smallest operand feasable).

    In this way we don't need to store the primes either.
 */

public class PrimeSum {
    
    public boolean isPrime(int num) {
        
        if(num < 1) return false;
        if(num == 1) return true;
        if(num == 2) return true;
        if(num == 3) return true;
        if(num % 2 == 0) return false;
        if(num % 3 == 0) return false;
        
        // sqrt does A LOT
        for(int i=5; i<Math.sqrt(num); i+=2) 
            if(num % i == 0) return false;
            
        return true;
    }
    
    public ArrayList<Integer> primesum(int a) {
        
        if(a % 2 == 1) return null;
        if(a <= 2) return null;
        
        ArrayList<Integer> primes = new ArrayList<Integer>();

        // Build list of first MAX_VALUE primes, Erastosthenes
        for(int i = 1; i<a; i++) {
            if(isPrime(i)) primes.add(i);
        }
        

        for(int i=0; i<primes.size(); i++) {
            
            int p1 = primes.get(i);
            if(p1 > a) break;
            
            for(int j=0; j<primes.size(); j++) {
            
                int p2 = primes.get(j);
                if(p1+p2 > a) break;
                if(p1+p2 == a) {
                    
                    ArrayList<Integer> res = new ArrayList<Integer>();
                    res.add(p1);
                    res.add(p2);
                    return res;
                };
            }
        }
        
        return null;
    }

    public ArrayList<Integer> primesumBetter(int a) {

        if(a % 2 == 1) return null;
        if(a <= 2) return null;

        ArrayList<Integer> res = new ArrayList<Integer>();

        for(int j=a-1; j >= 1; j--) {
            
            if(isPrime(j) && isPrime(a-j)) {
                
                res.add(a-j);
                res.add(j);
                break;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        
        PrimeSum ps = new PrimeSum();

        long l1, l2;
        int input = Integer.MAX_VALUE - 11;

        // Takes way too long
        l1 = System.currentTimeMillis();
//        System.out.println(ps.primesum(input));
        l2 = System.currentTimeMillis();

        System.out.println((l2 - l1) + " msec");

        // Terminates very fast
        l1 = System.currentTimeMillis();
        System.out.println(ps.primesumBetter(input));
        l2 = System.currentTimeMillis();

        System.out.println((l2 - l1) + " msec");
    }
}