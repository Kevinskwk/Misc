import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Board {

    private final int[][] tiles;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        int[][] tmpTiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmpTiles[i][j] = tiles[i][j];
            }
        }
        this.tiles = tmpTiles;
    }

    private int[][] copyTiles() {
        int[][] cloneTiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cloneTiles[i][j] = tiles[i][j];
            }
        }
        return cloneTiles;
    }
                                           
    // string representation of this board
    public String toString() {
        String ret = String.valueOf(n);
        ret += "\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret += " ";
                ret += String.valueOf(tiles[i][j]);
            }
            ret += "\n";
        }
        
        return ret;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int dist = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != i * n + j + 1)
                    dist++;
            }
        }
        return dist;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int dist = 0;
        int curr;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                curr = tiles[i][j];
                if (curr == 0)
                    continue;
                dist += Math.abs((curr - 1) / n - i) + Math.abs((curr - 1) % n - j);
            }
        }
        return dist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0 && (i == n-1 && j == n-1))
                    return true;
                if (tiles[i][j] != i * n + j + 1)
                    return false;
                
            }
        }
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;

        if (y == null) return false;

        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (this.n != that.n) return false;
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != that.tiles[i][j])
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> res = new ArrayList<Board>();
        int[] pos = getZeroPos();
        int x = pos[0];
        int y = pos[1];

        int[][] tmpTiles;
        if (x > 0) {
            tmpTiles = this.copyTiles();
            tmpTiles[x][y] = tiles[x-1][y];
            tmpTiles[x-1][y] = tiles[x][y];
            res.add(new Board(tmpTiles));
        }
        if (x < n-1) {
            tmpTiles = this.copyTiles();
            tmpTiles[x][y] = tiles[x+1][y];
            tmpTiles[x+1][y] = tiles[x][y];
            res.add(new Board(tmpTiles));
        }
        if (y > 0) {
            tmpTiles = this.copyTiles();
            tmpTiles[x][y] = tiles[x][y-1];
            tmpTiles[x][y-1] = tiles[x][y];
            res.add(new Board(tmpTiles));
        }
        if (y < n-1) {
            tmpTiles = this.copyTiles();
            tmpTiles[x][y] = tiles[x][y+1];
            tmpTiles[x][y+1] = tiles[x][y];
            res.add(new Board(tmpTiles));
        }
        
        return res;
    }

    // return an array of size two with x, y of tile zero
    private int[] getZeroPos() {
        int[] pos = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        throw new NoSuchElementException("no zero on board");
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] tmpTiles = this.copyTiles();
        if (tmpTiles[0][0] == 0) {
            tmpTiles[1][0] = tiles[0][1];
            tmpTiles[0][1] = tiles[1][0];
        } else if (tmpTiles[0][1] == 0) {
            tmpTiles[1][0] = tiles[0][0];
            tmpTiles[0][0] = tiles[1][0];
        } else {
            tmpTiles[0][0] = tiles[0][1];
            tmpTiles[0][1] = tiles[0][0];
        }
        return new Board(tmpTiles);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board test = new Board(tiles);
        System.out.println(test.dimension());
        System.out.println(test.toString());
        System.out.println(test.hamming());
        System.out.println(test.manhattan());
        System.out.println(test.isGoal());
        Iterable<Board> neighbors = test.neighbors();
        for (Board neighbor : neighbors) {
            System.out.println(neighbor.toString());
        }
        System.out.println(test.twin().toString());
    }

}