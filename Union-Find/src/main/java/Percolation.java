public class Percolation {

    private final int n;
    private final int size;
    private final int virtualTop;
    private final boolean[][] opened;
    private final WeightedQuickUnionUF uf;
    private final boolean[] containsBottomSite;

    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("N value must be positive");
        }

        this.n = n;
        this.size = (n * n) + 1;

        this.virtualTop = 0;

        this.uf = new WeightedQuickUnionUF(size);

        this.opened = new boolean[n + 1][n + 1];
        this.containsBottomSite = new boolean[size];
    }

    public void open(int i, int j) {
        checkIndex(i);
        checkIndex(j);
        if (!opened[i][j]) {
            opened[i][j] = true;

            int first = uf.find(xyTo1D(i, j));

            if ((i - 1 > 0) && opened[i - 1][j]) {
                uf.union(xyTo1D(i, j), xyTo1D(i - 1, j));
                int second = uf.find(xyTo1D(i - 1, j));
                if (containsBottomSite[first] || containsBottomSite[second]) {
                    containsBottomSite[first] = true;
                    containsBottomSite[second] = true;
                }
            }
            if ((i + 1 <= n) && opened[i + 1][j]) {
                uf.union(xyTo1D(i, j), xyTo1D(i + 1, j));
                int second = uf.find(xyTo1D(i + 1, j));
                if (containsBottomSite[first] || containsBottomSite[second]) {
                    containsBottomSite[first] = true;
                    containsBottomSite[second] = true;
                }
            }
            if ((j - 1 > 0) && opened[i][j - 1]) {
                uf.union(xyTo1D(i, j), xyTo1D(i, j - 1));
                int second = uf.find(xyTo1D(i, j - 1));
                if (containsBottomSite[first] || containsBottomSite[second]) {
                    containsBottomSite[first] = true;
                    containsBottomSite[second] = true;
                }
            }
            if ((j + 1 <= n) && opened[i][j + 1]) {
                uf.union(xyTo1D(i, j), xyTo1D(i, j + 1));
                int second = uf.find(xyTo1D(i, j + 1));
                if (containsBottomSite[first] || containsBottomSite[second]) {
                    containsBottomSite[first] = true;
                    containsBottomSite[second] = true;
                }
            }
            if (i == 1) {
                uf.union(xyTo1D(i, j), virtualTop);
            }

            if (i == n) {
                containsBottomSite[uf.find(xyTo1D(n, j))] = true;
            }
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
            return uf.connected(xyTo1D(i, j), virtualTop);
        }
        return false;
    }

    public boolean percolates() {
        return containsBottomSite[uf.find(virtualTop)];
    }

    private int xyTo1D(int i, int j) {
        return (i - 1) * n + j;
    }

    private void checkIndex(int index) {
        if (index <= 0 || index > n) {
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);
        }
    }

}
