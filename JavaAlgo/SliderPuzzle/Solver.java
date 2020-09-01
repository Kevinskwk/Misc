import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private boolean solvable;
    private Node end;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();

        MinPQ<Node> originPQ = new MinPQ<Node>(new ManhattanOrder());
        MinPQ<Node> twinPQ = new MinPQ<Node>(new ManhattanOrder());
        originPQ.insert(new Node(initial, null));
        twinPQ.insert(new Node(initial.twin(), null));

        Node currNode;
        Board currBoard;
        while (true) {
            currNode = originPQ.delMin();
            currBoard = currNode.board;
            if (currBoard.isGoal()) {
                solvable = true;
                end = currNode;
                break;
            } else {
                for (Board neighbor : currBoard.neighbors()) {
                    if (currNode.prev == null || !(neighbor.equals(currNode.prev.board)))
                        originPQ.insert(new Node(neighbor, currNode));
                }
            }

            currNode = twinPQ.delMin();
            currBoard = currNode.board;
            if (currBoard.isGoal()) {
                solvable = false;
                break;
            } else {
                for (Board neighbor : currBoard.neighbors()) {
                    if (currNode.prev == null || !(neighbor.equals(currNode.prev.board)))
                        twinPQ.insert(new Node(neighbor, currNode));
                }
            }
        }
    }

    private class Node {

        public final Board board;
        public final int moves;
        public final Node prev;
        private final int manhattan;

        public Node(Board board, Node prev) {
            this.board = board;
            if (prev == null)
                this.moves = 0;
            else
                this.moves = prev.moves + 1;
            this.prev = prev;
            this.manhattan = board.manhattan() + this.moves;
        }
    }

    private class ManhattanOrder implements Comparator<Node> {

        public int compare(Node n1, Node n2) {
            if (n1.manhattan > n2.manhattan)
                return 1;
            else if (n1.manhattan < n2.manhattan)
                return -1;
            else
                return 0;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!solvable)
            return -1;
        return end.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!solvable)
            return null;

        Board[] sol = new Board[this.moves()+1];
        Node curr = end;
        for (int i = this.moves(); i >= 0; i--) {
            sol[i] = curr.board;
            curr = curr.prev;
        }
        return Arrays.asList(sol);
    }

    // test client (see below) 
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}