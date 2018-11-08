import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/*
    Count all possible position that can be reached by Modified Knight
    Given a chessboard of size 8 x 8 and the current position of Mirandote. 
    All the rules of this chess game are same but the knight is modified, we call new knight as “Mirandote”. 


    The task is to find how many possible positions exist in Chessboard that can be reached by Mirandote in exactly S steps.

    Examples, 8x8 board:

    Input: row = 4, col = 4, steps = 1
    Output: 12

    Input: row = 4, col = 4, steps = 2
    Output: 55

    Proposal:

    Recursive approach:
    write a function that computes all the cells reachable by Mirandote;
    call this function recursively decreasing the steps until a base condition is reached:
        the base condition is a single cell: if the cell is on the board, add it to the solution
    
    For the solution I used a Set, since by math's definition has no duplicates (we should not consider the
    same cells twice)
*/


class Pair {
    /*
        Helper class to store pairs of coordinates
    */

    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair sumPair(Pair p1) {

        return new Pair(p1.x + this.x, p1.y + this.y);
    }

    /*
        Needed to override these functions so this class can be propoperly used by Set to maintain the
        invariance of no duplicates
     */
    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Pair))return false;
        Pair otherPair = (Pair) other;

        return otherPair.x == this.x && otherPair.y == this.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(new Integer(x), new Integer(y));
    }
}

public class ModifiedKnight {

    public Pair             boardSizes  = new Pair(8, 8);
    public ArrayList<Pair>  offesets    = new ArrayList<Pair>();

    public ModifiedKnight() {

        offesets.add(new Pair(1, 1));
        offesets.add(new Pair(2, 1));
        offesets.add(new Pair(1, 2));

        offesets.add(new Pair(-1, 1));
        offesets.add(new Pair(-2, 1));
        offesets.add(new Pair(-1, 2));

        offesets.add(new Pair(1, -1));
        offesets.add(new Pair(2, -1));
        offesets.add(new Pair(1, -2));

        offesets.add(new Pair(-1, -1));
        offesets.add(new Pair(-2, -1));
        offesets.add(new Pair(-1, -2));
    }

    // Check whether a cell is on the board
    private boolean isInBoard(Pair pt) {

        return ((pt.x >= 0 && pt.y >= 0) && (pt.x < boardSizes.x && pt.y < boardSizes.y));
    }


    public HashSet<Pair> possiblePositions(Pair start, int steps) {

        if(steps == 0) {
            // Base condition
            HashSet<Pair> l = new HashSet<Pair>();
            if(isInBoard(start)) l.add(start);
            return l;
        }

        HashSet<Pair> positions = new HashSet<Pair>();

        for(Pair p: offesets)
            positions.addAll(possiblePositions(start.sumPair(p), steps-1));

        return positions;
    }


    public static void main(String[] args) {
        
        ModifiedKnight mk = new ModifiedKnight();

        Pair start = new Pair(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        HashSet<Pair> pos = mk.possiblePositions(start, Integer.parseInt(args[2]));

        System.out.println(pos.size());
   
        for(Pair p: pos)
            System.out.println(p.x + " - " + p.y);
    }    
}