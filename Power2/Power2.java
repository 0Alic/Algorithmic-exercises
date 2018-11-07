/*
    POWER2
    Given a positive integer which fits in a 32 bit signed integer, 
    find if it can be expressed as A^P where P > 1 and A > 0.
    A and P both should be integers.

    Example

    Input : 4
    Output : True  
    as 2^2 = 4.

    
    Solution

    This problem can be solved naively with "brute force".
    We iterate with A and P and we check if input == A^P.
    But we can make a few optimization though.
    
    First of all, we can see that number (a == A^P) && (a < 0), then (-A^P == -a), thus
    since we want to return just a true/false, we can avoid all the negative numbers by
    making the input = -input: doing so we skip 2G numbers (i.e. all the negative numbers of a 32bits int).

    Second, we can shrink the outer loop, the A in (1, Integer.MAX_VALUE). Since A,P are integers, then
    for all the numbers A above the square root of Integer.MAX_VAlUE, since P > 1, A^P will be greater than Integer.MAX_VALUE.
    In this way we can stop our loop as soon as it reaches the sqrt(Integer.MAX_VALUE), which is 46340 instead of 2147483647.

    Complexity = O(sqrt(2G) * 32)
*/

public class Power2 {
    
    public boolean isPower(int a) {
        
        if(a == 0) return false;
        if(a == 1 || a ==  -1) return true;
        
        if(a < 0) a = -a;
        
        int max = Integer.MAX_VALUE;
        int maxIter = (int) Math.sqrt(max);
        
        for(int A = 1; A < maxIter; A++) {

            if(A > a) break;
            
            for(int P = 2; P < 32; P++) {
                
                double res = Math.pow((double) A, (double) P);
                if(res > a) break;
                if(res > max) break;
                
                if(res == a) return true;
            }
        }
        
        return false;
    }
}