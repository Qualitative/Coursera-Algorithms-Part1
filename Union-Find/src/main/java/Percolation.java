public class Percolation {

    private final int N;
    private final int size;
    private final int virtualTop;
    private final int virtualBottom;;
    private final boolean[][] opened;
    private final WeightedQuickUnionUF ufTop;
    private final WeightedQuickUnionUF ufBottom;

    public Percolation(int N) {

        if (N <= 0)
            throw new IllegalArgumentException("N value must be positive");

        this.N = N;
        this.size = (N * N) + 2;

        this.virtualTop = 0;
        this.virtualBottom = size - 1;

        this.ufTop = new WeightedQuickUnionUF(size);
        this.ufBottom = new WeightedQuickUnionUF(size);

        this.opened = new boolean[N + 1][N + 1];
    }

    public void open(int i, int j) {
        checkIndex(i);
        checkIndex(j);
        if (!opened[i][j]) {
            opened[i][j] = true;
            if ((i - 1 > 0) && opened[i - 1][j]) {
                ufTop.union(xyTo1D(i, j), xyTo1D(i - 1, j));
                ufBottom.union(xyTo1D(i, j), xyTo1D(i - 1, j));
            }
            if ((i + 1 <= N) && opened[i + 1][j]) {
                ufTop.union(xyTo1D(i, j), xyTo1D(i + 1, j));
                ufBottom.union(xyTo1D(i, j), xyTo1D(i + 1, j));
            }
            if ((j - 1 > 0) && opened[i][j - 1]) {
                ufTop.union(xyTo1D(i, j), xyTo1D(i, j - 1));
                ufBottom.union(xyTo1D(i, j), xyTo1D(i, j - 1));
            }
            if ((j + 1 <= N) && opened[i][j + 1]) {
                ufTop.union(xyTo1D(i, j), xyTo1D(i, j + 1));
                ufBottom.union(xyTo1D(i, j), xyTo1D(i, j + 1));
            }
            if (i == 1) {
                ufTop.union(xyTo1D(i, j), virtualTop);
                ufBottom.union(xyTo1D(i, j), virtualTop);
            }
            if (i == N)
                ufBottom.union(xyTo1D(i, j), virtualBottom);
        }
    }

    public boolean isOpen(int i, int j) {
        checkIndex(i);
        checkIndex(j);
        return opened[i][j];
    }

    public boolean isFull(int i, int j) {
        checkIndex(i);
        checkIndex(j);
        if (opened[i][j]) {
            return ufTop.connected(xyTo1D(i, j), virtualTop);
        }
        return false;
    }

    public boolean percolates() {
        return ufBottom.connected(virtualTop, virtualBottom);
    }

    private int xyTo1D(int i, int j) {
        return (i - 1) * N + j;
    }

    private void checkIndex(int index) {
        if (index <= 0 || index > N)
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);
    }

}
