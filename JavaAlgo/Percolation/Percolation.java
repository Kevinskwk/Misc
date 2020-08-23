import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private boolean[] grid;
    private final int n;
    private int numOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(2+n*n);
        grid = new boolean[n*n];
        this.n = n;
        numOpen = 0;
    }

    // checks whether coordinates are valid
    private boolean validate(final int row, final int col) {
        if ((row > n) || (row < 1) || (col > n) || (col < 1)) {
            return false;
        }
        return true;
    }

    // opens the site (row, col) if it is not open already
    public void open(final int row, final int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            grid[(row - 1) * n + col - 1] = true;
            numOpen++;
            if (row == 1) {
                uf.union(0, col);
            }
            if (row == n) {
                uf.union(n*n+1, n*(n-1)+col);
            }

            if ((row > 1) && (isOpen(row - 1, col))) {
                uf.union((row - 1) * n + col, (row - 2) * n + col);
            }
            if ((row < n) && (isOpen(row + 1, col))) {
                uf.union((row - 1) * n + col, row * n + col);
            }
            if ((col > 1) && (isOpen(row, col - 1))) {
                uf.union((row - 1) * n + col, (row - 1) * n + col - 1);
            }
            if ((col < n) && (isOpen(row, col + 1))) {
                uf.union((row - 1) * n + col, (row - 1) * n + col + 1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(final int row, final int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        return grid[(row - 1) * n + col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(final int row, final int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        return uf.find(0) == uf.find((row - 1) * n + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(n*n + 1);
    }

    // test client (optional)
    public static void main(final String[] args) {
        Percolation test = new Percolation(1);
        System.out.println(test.isFull(1, 1));
        System.out.println(test.isOpen(1, 1));
        System.out.println(test.percolates());
        test.open(1, 1);
        System.out.println(test.isFull(1, 1));
        System.out.println(test.isOpen(1, 1));
        System.out.println(test.percolates());
    }
}