

/**
 * @author Qualitative
 *
 * The indices i and j are integers between 1 and N, where (1, 1) is the upper-left site.
 * Throw a java.lang.IndexOutOfBoundsException if either i or j is outside this range.
 * The constructor should take time proportional to N^2.
 * All methods should take constant time plus a constant number of calls to the union-find methods union(), find(), connected(), and count().
 */
public class Percolation {

    public Percolation(int N) {             // create N-by-N grid, with all sites blocked
        // TODO Auto-generated constructor stub
    }

    public void open(int i, int j) {        // open site (row i, column j) if it is not already
        // TODO Auto-generated method stub
    }

    public boolean isOpen(int i, int j) {   // is site (row i, column j) open?
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isFull(int i, int j) {   // is site (row i, column j) full?
        // TODO Auto-generated method stub
        return false;
    }

    public boolean percolates() {           // does the system percolate?
        // TODO Auto-generated method stub
        return false;
    }

}
