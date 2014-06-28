
public class PercolationStats {

    private final int t;
    private double[] fractions;

    public PercolationStats(int n, int t) {

        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("N and T values must be positive");
        }

        this.t = t;
        this.fractions = new double[t];

        for (int i = 0; i < t; i++) {

            Percolation perc = new Percolation(n);
            int sitesCount = 0;

            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (perc.isOpen(row, col)) {
                    continue;
                }
                perc.open(row, col);
                sitesCount++;
            }

            fractions[i] = (double) sitesCount / (n * n);
        }

    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        if (t == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }
}
