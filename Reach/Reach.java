/*
 You are in an infinite 2D grid where you can move in any of the 8 directions :
 
  (x,y) to 
     (x+1, y), 
     (x - 1, y), 
     (x, y+1), 
     (x, y-1), 
     (x-1, y-1), 
     (x+1,y+1), 
     (x-1,y+1), 
     (x+1,y-1) 
 You are given a sequence of points and the order in which you need to cover the points. 
 Give the minimum number of steps in which you can achieve it. You start from the first point.
 
 Example :
 
 Input : [(0, 0), (1, 1), (1, 2)]
 Output : 2
 It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
 
*/

public class Reach {

    /*
        Finding the distance between two points in a grid considering only "horizontal" and "vertical" moves is equal to
        compute the Manhattan distance between these two points.
        Since we have also diagonal movements then the formula doesn't not hold properly.
        What I have done to adapt it was to compute the minimum distance difference between X and Y axis of the two points and
        virtually "translate" diagonally the point 1 towards the point 2, compute the mahattan distance between these 2 points
        (actually , they end up to be on the same vertical or horizontal axis, one component of the distance formula would be 0,
        but doing like this I can avoid an "if" statement)

        Iterate this procedure for all pair of points, in sequence, to get the minimum steps
    */

   
    public int absSub(int a, int b) {
        
        int c = a-b;
        if(c < 0) return -c;
        
        return c;
    }
    
    public int min(int a, int b) {
        
        if(a <= b) return a; else return b;
    }
    
    // Improperly called manhattan, but you get the point
    public int manhattan(int x1, int y1, int x2, int y2) {
        
        int xDiff = absSub(x1, x2);
        int yDiff = absSub(y1, y2);
        
        int minDiff = min(xDiff, yDiff);
        
        if(x1 < x2) x1 += minDiff;
        else x1 -= minDiff;
            
        if(y1 < y2) y1 += minDiff;
        else y1 -= minDiff;        
        
        return minDiff + (absSub(x1, x2) + absSub(y1, y2));
    }
    

    // X and Y co-ordinates of the points in order.
    // Each point is represented by (X.get(i), Y.get(i))
    public int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
        
        if(X == null || Y == null) return 0;
        if(X.size() == 0 || Y.size() == 0) return 0;
        if(X.size() != Y.size()) return 0;
        
        int steps = 0;
        
        for(int i=0; i<X.size()-1; i++) {
            
            steps += manhattan(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
        }
        
        return steps;
    }
}
