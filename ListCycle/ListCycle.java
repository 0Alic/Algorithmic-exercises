/*	
	Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

	Try solving it using constant additional space.

	Example :

	Input : 

					______
					|     |
					\/    |
			1 -> 2 -> 3 -> 4

	Return the node corresponding to node 3. 
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class ListCycle {
	public ListNode detectCycle(ListNode a) {
	    
	    // Basic testcase
	    if(a == null || a.next == null)
	        return null;
	    
	    // Detect a cycle: use two pointers, one moving with "one step" the other twice as fast
	    // If none of them will meet a null, then there is a cycle and soon or later they will meet
	    // So, if they meet, there is a cycle.. somewhere
	    ListNode one = a;
	    ListNode two = a.next;
	    
	    while(one != two) {
	        
	        if(two.next == null || two.next.next == null)
	            return null;
	        
	        one = one.next;
	        two = two.next.next;
	    } 
	    
	    // There is a cycle
	    int k = 1;
	    two = two.next;

        // I compute the size of the cycle
	    while(two != one) {
	        two = two.next;
	        k++;
	    }
	    
	    // I find the start of the cycle
	        // Reset one at the beginning
	    one = a;
	    two = a;
	        // Set two at k-th position
	    for(int i=0; i<=k; i++)
    	    two = a.next;
    	    
    	while(one != two) {
    	    
    	    one = one.next;
    	    two = two.next;
    	}
    	
	    
	    return one;
	}
}
