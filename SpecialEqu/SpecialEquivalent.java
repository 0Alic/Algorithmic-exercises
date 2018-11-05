import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Given an array of lower case strings, the task is to find the number of strings that are special equivalent. 
    Two strings are special equivalent if they can be made equivalent by performing some operations on one or both string 

    swapEven : swap a character at an even-numbered index with a character at another even-numbered index 

    swapOdd : swap a character at an odd-numbered index with a character at another odd-numbered index 


    Input : arr = {"abcd", "cbad", "bacd"} 
    Output : 2 
        The 2nd string can be converted to the 1st by swapping the first and third characters. So there are 2 distinct 
        strings as the third string cannot be converted to the first. 


    Input = {"abcd", "acbd", "adcb", "cdba", "bcda", "badc"}; 
    Output : 4
*/
public class SpecialEquivalent {


    /*
        Actually I don't need the methods, their definitions just gave me the idea of the property of "special equivalet"

            I have implemented them anyway to give them a place in the solution :)
    */
    private String swap(String s, int i, int j) {

        char[] characters = s.toCharArray();
        char c = characters[i];
        characters[i] = characters[j];
        characters[j] = c;
        return new String(characters);
    }

    public String swapEven(String s, int i, int j) {

        if(i % 2 == 0 && j % 2 == 0 && (i < s.length() && j < s.length()))
            return swap(s, i, j);
        else
            System.out.println("Even: bad input");

        return s;
    }

    public String swapOdd(String s, int i, int j) {

        if(i % 2 == 1 && j % 2 == 1 && (i < s.length() && j < s.length()))
            return swap(s, i, j);      
        else
            System.out.println("Odd: bad input");

        return s;
    }

    private void updateMap(Map<Character, Integer> map, Character key) {

        Integer v = map.get(key);
        if(v == null)
            map.put(key, 1);
        else
            map.put(key, new Integer(++v));
    }

    // If two strings are special equivalent, it means that the two strings have the same set of characters at even positions (same for odd)
    public boolean special(String s1, String s2) {

        if(s1.length() != s2.length()) return false;

        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        // Store list of even/odd characters for each string

        // Now I could store the lists of even/odd character for each string and perform double loops on each list couple
            // O(N^2) more or less (N == length of lists)
        // Or I could sort these lists and check if they are equal
            // O(N log N), in-place
        // Or I could count occurrences of each character and see if I get the same number of characters AND occurrences
            // O(M) time, O(M) storage (M == length of original strings)

        HashMap<Character, Integer> lp1_even = new HashMap<Character, Integer>();
        HashMap<Character, Integer> lp2_even = new HashMap<Character, Integer>();
        HashMap<Character, Integer> lp1_odd = new HashMap<Character, Integer>();
        HashMap<Character, Integer> lp2_odd = new HashMap<Character, Integer>();

        for(int i = 0; i < cs1.length; i++) {

            // store the character and its occurrence
            if(i % 2 == 0) {

                updateMap(lp1_even, cs1[i]);
                updateMap(lp2_even, cs2[i]);    
            }
            else {

                updateMap(lp1_odd, cs1[i]);
                updateMap(lp2_odd, cs2[i]);    
            }
        }

        /*
        System.out.println("Even Map: String 1");
        System.out.println(lp1_even);
        System.out.println("Even Map: String 2");
        System.out.println(lp2_even);
        System.out.println("Odd Map: String 1");
        System.out.println(lp1_odd);
        System.out.println("Odd Map: String 2");
        System.out.println(lp2_odd);
        */

        // Count occurrences
        for(Character c : lp1_even.keySet()) {

            // If the key matches different values, return false 
            if(!lp1_even.get(c).equals(lp2_even.get(c)))
                return false;

        }

        for(Character c : lp1_odd.keySet()) {

            // If the key matches different values, return false 
            if(!lp1_odd.get(c).equals(lp2_odd.get(c)))
                return false;

        }

        // The two pair of maps should be identical, return true
        return true;
    }

    public static void main(String[] args) {

        SpecialEquivalent se = new SpecialEquivalent();

        for(int i = 0; i<args.length; i++) {
            for(int j = i+1; j<args.length; j++) {

                System.out.println(args[i] + " - " + args[j] + "? " + se.special(args[i], args[j]));
            }
        }
    }
}