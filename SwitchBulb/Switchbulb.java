/*
N light bulbs are connected by a wire. 
Each bulb has a switch associated with it, however due to faulty wiring, 
a switch also changes the state of all the bulbs to the right of current bulb.

Given an initial state of all bulbs, find the minimum number of switches you have to press to turn on all the bulbs. 
You can press the same switch multiple times.

Note : 0 represents the bulb is off and 1 represents the bulb is on.

Example:

Input : [0 1 0 1]
Return : 4

Explanation :
	press switch 0 : [1 0 1 0]
	press switch 1 : [1 1 0 1]
	press switch 2 : [1 1 1 0]
    press switch 3 : [1 1 1 1]
    
*/

public class SwitchBulb {
    
    /*
        Let's see some examples before writing down my approach. I put a -* to the bit to switch, according to the question

        [1 0 0 0] >> [1 -0 0 0] => [1 1 1 1] :: 1 move
        [0 0 1 1] >> [-0 0 1 1] => [1 1 -0 0] => [1 1 1 1] :: 2 moves
        [1 0 0 1] >> [1 0 0 -1] => [1 -0 0 0] => [1 1 1 1] :: 2 moves
        [1 0 1 1] >> [1 -0 1 1] => [1 1 -0 0] => [1 1 1 1] :: 2 moves
        [1 0 1 0] >> [1 -0 1 0] => [1 1 -0 1] => [1 1 1 -0] => [1 1 1 1] :: 3 moves

        I did notice a pattern, but before I explain a bit my thought-flow
        First of all, I though "can it be done linearly?". From hand tests, seems so. Also, no additional data struct seems to be needed
        Second, I thought that the decimal representation of the values could help. But I didn't see any correlation.
        
        Then I noticed: it's straightforward find array of only 1s, and I see more 1 and 0 alternates, more moves I need.
        So I counted the number of 0 -> 1 and 1 -> 0 and I saw that this is a number close to the minimun number of nodes. Actually, equal or 1 value less
        I noticed that if the array starts with 0, then I need to press that first bit in any case, because it won't be turned on by the others.

        Final thought: the solution is number of positions 0 -> 1, 1 -> 0 and if a[0] == 0, add 1
    */
    public int bulbs(ArrayList<Integer> a) {
        
        if(a.size() == 0) return 0;

        int c = 0;
        
        if(a.get(0) == 0) c++;
        
        for(int i=0; i < a.size()-1; i++ ) {
            
            if(a.get(i) != a.get(i+1)) c++;
        }
        
        return c;
    }
}